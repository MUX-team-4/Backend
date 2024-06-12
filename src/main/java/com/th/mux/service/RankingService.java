package com.th.mux.service;

import com.th.mux.dto.RankingDto;
import com.th.mux.dto.StatisticDto;
import com.th.mux.dto.TimePeriodDto;
import com.th.mux.model.Department;
import com.th.mux.model.Ranking;
import com.th.mux.model.User;
import com.th.mux.repository.DepartmentRepository;
import com.th.mux.repository.RankingRepository;
import com.th.mux.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
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

    public List<Ranking> getRankings() {
        //(r1, r2)  ->  Integer.compare(r1.getSteps(), r2.getSteps())
        return rankingRepository.findAll().stream().sorted(Comparator.comparingLong(Ranking::getSteps))
                .collect(Collectors.toList());
    }

    /**
     * For total tab
     * @return
     */
    public List<RankingDto> getRankingsGroupByDepartment() {
        //(r1, r2)  ->  Integer.compare(r1.getSteps(), r2.getSteps())
        return rankingRepository.getRankings().stream()
                .map(objects -> {
                    long departmentId = (long) objects[0];
                    Department department = departmentRepository.findById(departmentId).get();
                    long steps = (long) objects[1];
                    return new RankingDto(0, department.getId(), department.getName(), steps, null);
                })
                .sorted(Comparator.comparingLong(RankingDto::getSteps).reversed())
                .collect(Collectors.toList());
    }

    public List<RankingDto> getRankingsGroupByDepartment(TimePeriodDto timePeriodDto) {
        return rankingRepository.getRankingsByTimePeriod(timePeriodDto.getFromDate(), timePeriodDto.getToDate()).stream()
                .map(objects -> {
                    long departmentId = (long) objects[0];
                    Department department = departmentRepository.findById(departmentId).get();
                    long steps = (long) objects[1];
                    return new RankingDto(0, department.getId(), department.getName(), steps, null);
                }).sorted(Comparator.comparingLong(RankingDto::getSteps).reversed())
                .collect(Collectors.toList());
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
    }}
