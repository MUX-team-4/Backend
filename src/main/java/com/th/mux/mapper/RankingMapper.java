package com.th.mux.mapper;

import com.th.mux.dto.CourtDto;
import com.th.mux.dto.RankingDto;
import com.th.mux.model.Court;
import com.th.mux.model.Ranking;

public class RankingMapper {
    public static RankingDto toDto(Ranking ranking) {
        if (ranking == null) {
            return null;
        }
        return new RankingDto(ranking.getId(), ranking.getDepartment().getId(), ranking.getSteps(),
                ranking.getDate());
    }
}
