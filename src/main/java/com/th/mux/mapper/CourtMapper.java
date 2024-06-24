package com.th.mux.mapper;

import com.th.mux.dto.CourtDto;
import com.th.mux.model.Court;

public class CourtMapper {

    public static CourtDto toDto(Court court) {
        if (court == null) {
            throw new RuntimeException("Input is invalid");
        }
        return new CourtDto(court.getId(), court.getName());
    }

    public static Court toCourt(CourtDto courtDto) {
        if (courtDto == null) {
            throw new RuntimeException("Input is invalid");
        }
        Court court = new Court();
        court.setId(courtDto.getId());
        court.setName(courtDto.getName());
        return court;
    }
}
