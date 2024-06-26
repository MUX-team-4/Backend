package com.th.mux.service;

import com.th.mux.dto.RankingDto;
import com.th.mux.dto.StatisticDto;
import com.th.mux.dto.TimePeriodDto;
import com.th.mux.model.Department;
import com.th.mux.model.Ranking;
import com.th.mux.model.Trend;
import com.th.mux.model.User;
import com.th.mux.repository.DepartmentRepository;
import com.th.mux.repository.RankingRepository;
import com.th.mux.repository.UserRepository;
import com.th.mux.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
@Slf4j
public class RankingService {
    private final RankingRepository rankingRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public RankingService(RankingRepository rankingRepository, DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.rankingRepository = rankingRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    /**
     * For total tab (gesamt)
     * @return
     */
    public List<RankingDto> getRankingsGroupByDepartment() {
        //(r1, r2)  ->  Integer.compare(r1.getSteps(), r2.getSteps())
        List<RankingDto> rankingIncludeToday = convertToRankingDtos(rankingRepository.getRankings());
        List<RankingDto> rankingExcludeToday = convertToRankingDtos(rankingRepository.getRankingsExcludeToday());
        return calculateTrend(rankingIncludeToday, rankingExcludeToday);

    }

    public List<RankingDto> getRankingsGroupByDepartment(TimePeriodDto timePeriodDto) {
        List<RankingDto> rankingIncludeToday = convertToRankingDtos(rankingRepository.getRankingsByTimePeriod(timePeriodDto.getFromDate(), timePeriodDto.getToDate()));
        List<RankingDto> rankingExcludeToday = convertToRankingDtos(rankingRepository.getRankingsByTimePeriod(timePeriodDto.getFromDate(), LocalDate.now()));
        return calculateTrend(rankingIncludeToday, rankingExcludeToday);
    }

    /**
     * Caculate Trend based on today list and yesterday list
     * @param rankingIncludeToday
     * @param rankingExcludeToday
     * @return
     */
    private List<RankingDto> calculateTrend(List<RankingDto> rankingIncludeToday, List<RankingDto> rankingExcludeToday) {
        if (rankingIncludeToday == null || rankingIncludeToday.isEmpty()) {
            log.info("rankingIncludeToday == null || rankingIncludeToday.isEmpty()");
            return null;
        }
        if (rankingExcludeToday == null || rankingExcludeToday.isEmpty()) {
            log.info("rankingExcludeToday == null || rankingExcludeToday.isEmpty()");
            // no information for yesterday -> Trend = Improved
            rankingIncludeToday.forEach(item -> item.setTrend(Trend.VERBESSERT));
            return rankingIncludeToday;
        }
        for (int i = 0; i < rankingIncludeToday.size(); i++) {
            int indexFound = rankingExcludeToday.indexOf(rankingIncludeToday.get(i));
            Trend trend;
            if (indexFound != Constant.NOT_FOUND_INDEX) {
                log.info("index in include ={}, found index in prev={}", i, indexFound);
                if (i > indexFound ) {
                    // worse
                    trend = Trend.VERSCHLECHTERT;
                } else if (i == indexFound) {
                    trend = Trend.GLEICH;
                } else {
                    trend = Trend.VERBESSERT;
                }
            } else {
                // default is improved
                trend = Trend.VERBESSERT;
            }
            rankingIncludeToday.get(i).setTrend(trend);
        }
        return rankingIncludeToday;
    }

    /**
     * Formatted Objects to Ranking data transfer objects
     * @param objects
     * @return
     */
    private List<RankingDto> convertToRankingDtos(Optional<List<Object[]>> objects) {
        return objects.map(items -> items.stream()
                .map(item -> {
                    Department department = (Department) item[0];
                    //log.info("department={}", department.getName());
                    //Department department = departmentRepository.findById(departmentId).get();
                    long steps = (long) item[1];
                    return new RankingDto(Constant.NO_ID, department.getId(), department.getName(), steps, null, null);
                }).collect(Collectors.toList())).orElse(null);
        //.sorted(Comparator.comparingLong(RankingDto::getSteps).reversed())
    }

    /**
     * Check to update or insert ranking to database
     * @param statisticDto
     * @return
     */
    public boolean updateRanking(StatisticDto statisticDto) {
        // can find by email
        Optional<User> userOptional = userRepository.findById(statisticDto.getUserId());
        if (userOptional.isPresent()) {
            // check department id
            Optional<Department> departmentOptional = departmentRepository.findById(userOptional.get().getDepartment().getId());
            if (departmentOptional.isPresent()) {
                Optional<Ranking> rankingOptional = rankingRepository.findRankingByIdAndDate(departmentOptional.get(), statisticDto.getDate());
                Ranking ranking;
                if (rankingOptional.isPresent()) {
                    log.info("update ranking");
                    // update ranking
                    ranking = rankingOptional.get();
                    ranking.setSteps(ranking.getSteps() + statisticDto.getSteps());
                } else {
                    log.info("insert new ranking");
                    // insert new ranking
                    ranking = new Ranking();
                    ranking.setDate(statisticDto.getDate());
                    ranking.setSteps(statisticDto.getSteps());
                    ranking.setDepartment(departmentOptional.get());
                }
                rankingRepository.save(ranking);
                return true;
            } else {
                throw  new RuntimeException("Department not exist");
            }
        } else {
            throw  new RuntimeException("User not exist");
        }
    }

    /**
     * Update Ranking tabelle in database
     * @param statisticDtos
     */
    public void updateRankings(List<StatisticDto> statisticDtos) {
        if (statisticDtos == null) {
            return;
        }
        statisticDtos.forEach(this::updateRanking);
    }
}
