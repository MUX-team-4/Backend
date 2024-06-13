package com.th.mux.service;

import com.th.mux.dto.StatisticDto;
import com.th.mux.dto.TimePeriodDto;
import com.th.mux.mapper.StatisticMapper;
import com.th.mux.model.Statistic;
import com.th.mux.model.User;
import com.th.mux.repository.StatisticRepository;
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
@Slf4j
@Transactional(isolation = Isolation.SERIALIZABLE)
public class StatisticService {
    private final StatisticRepository statisticRepository;
    private final UserRepository userRepository;

    @Autowired
    public StatisticService(StatisticRepository statisticRepository, UserRepository userRepository) {
        this.statisticRepository = statisticRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public StatisticDto updateStatistic(StatisticDto dto) {
        // can find by email
        Optional<User> userOptional = userRepository.findById(dto.getUserId());
        Optional<Statistic> statisticOp = statisticRepository.findByIdAndDate(userOptional.get(), dto.getDate());
        //Optional<Statistic> statisticOp = statisticRepository.findById(dto.getId());
        //log.info("updateStatistic statisticOp={}", statisticOp);
        Statistic statistic;
        if (statisticOp.isPresent()) {
            // update value
            statistic = statisticOp.get();
            statistic.setSteps(dto.getSteps() + statisticOp.get().getSteps());
            statistic.setDistance(dto.getDistance());
        } else {
            // insert value
            statistic = new Statistic();
            userOptional.ifPresent(statistic::setUser);
            statistic.setDate(dto.getDate());
            statistic.setSteps(dto.getSteps());
            statistic.setDistance(dto.getDistance());
        }
        Statistic saved = statisticRepository.save(statistic);
        return StatisticMapper.toDto(saved);
    }

    /**
     *
     * @param userId
     * @return
     */
    public List<StatisticDto> getStatistics(long userId) {
        Optional<List<Statistic>> listOptional = statisticRepository.findByUserId(userId);
        return listOptional.map(statistics -> statistics.stream().map(StatisticMapper::toDto)
                .collect(Collectors.toList())).orElse(null);
    }

    /**
     *
     * @param userId
     * @param timePeriodDto
     * @return
     */
    public List<StatisticDto> getStatistics(long userId, TimePeriodDto timePeriodDto) {
        Optional<List<Statistic>> listOptional = statisticRepository.findByUserIdAndTimePeriod(userId,
                timePeriodDto.getFromDate(), timePeriodDto.getToDate());
        return listOptional.map(statistics -> statistics.stream().map(StatisticMapper::toDto)
                .collect(Collectors.toList())).orElse(null);
    }

//    public List<StatisticDto> getStatisticsGroupByDepartment(long departmentId) {
//        return statisticRepository.findAll().stream().filter(statistic -> statistic.getUser().getDepartment().getId() == departmentId)
//                .map(StatisticMapper::toDto)
//                .collect(Collectors.toList());
//    }

    // for ranking of users in a department
    public List<StatisticDto> getStatisticsGroupByDepartment(long departmentId) {
        Optional<List<Object[]>> objectsOp = statisticRepository.getStatisticGroupByUser();
        return getStatisticDtos(departmentId, objectsOp);
    }

    public List<StatisticDto> getStatisticGroupByUserAndTimePeriod(long departmentId, TimePeriodDto timePeriodDto) {
        Optional<List<Object[]>> objectsOp = statisticRepository.getStatisticGroupByUserAndTimePeriod(timePeriodDto.getFromDate(), timePeriodDto.getToDate());
        return getStatisticDtos(departmentId, objectsOp);
    }

    private List<StatisticDto> getStatisticDtos(long departmentId, Optional<List<Object[]>> objectsOp) {
        return objectsOp.map(objects -> objects.stream()
                .filter(item -> userRepository.findById((long) item[0]).get().getDepartment().getId() == departmentId)
                .map(item -> {
                    long userId = (long) item[0];
                    long steps = (long) item[1];
                    double distance = (double) item[2];
                    return new StatisticDto(0, userId, steps, distance, null);
                })
                .sorted(Comparator.comparingLong(StatisticDto::getSteps).reversed())
                .collect(Collectors.toList())).orElse(null);
    }
}
