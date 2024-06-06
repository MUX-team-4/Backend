package com.th.mux.service;

import com.th.mux.dto.StatisticDto;
import com.th.mux.mapper.StatisticMapper;
import com.th.mux.model.Statistic;
import com.th.mux.model.User;
import com.th.mux.repository.StatisticRepository;
import com.th.mux.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class StatisticService {
    private final StatisticRepository statisticRepository;
    private final UserRepository userRepository;

    @Autowired
    public StatisticService(StatisticRepository statisticRepository, UserRepository userRepository) {
        this.statisticRepository = statisticRepository;
        this.userRepository = userRepository;
    }


    public StatisticDto updateStatistic(StatisticDto dto) {
        Optional<User> userOptional = userRepository.findById(dto.getUserId());
        Optional<Statistic> statisticOp = statisticRepository.findByIdAndDate(userOptional.get(), dto.getDate());
        //Optional<Statistic> statisticOp = statisticRepository.findById(dto.getId());
        //log.info("updateStatistic statisticOp={}", statisticOp);
        Statistic statistic;
        if (statisticOp.isPresent()) {
            // update value
            statistic = statisticOp.get();
            statistic.setSteps(dto.getSteps());
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
}
