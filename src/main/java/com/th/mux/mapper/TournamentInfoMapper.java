package com.th.mux.mapper;

import com.th.mux.dto.TournamentInfoDto;
import com.th.mux.model.TournamentInfo;

public class TournamentInfoMapper {
    public static TournamentInfoDto toDto(TournamentInfo info) {
        if (info == null) {
            throw new RuntimeException("Input is invalid");
        }
        return new TournamentInfoDto(info.getTitle(), info.getDateStart(),
                info.getDateEnd(), info.getDescription());
    }
}
