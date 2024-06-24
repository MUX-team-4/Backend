package com.th.mux.mapper;

import com.th.mux.dto.StatisticDto;
import com.th.mux.model.Statistic;

public class StatisticMapper {
    public static StatisticDto toDto(Statistic statistic) {
        if (statistic == null) {
            throw new RuntimeException("Input is invalid");
        }
        StatisticDto dto = new StatisticDto();
        dto.setId(statistic.getId());
        dto.setUserId(statistic.getUser().getId());
        dto.setDate(statistic.getDate());
        dto.setSteps(statistic.getSteps());
        dto.setDistance(statistic.getDistance());
        return dto;
    }
}
