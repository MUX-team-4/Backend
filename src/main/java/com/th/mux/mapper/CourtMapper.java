package com.th.mux.mapper;

import com.th.mux.dto.CourtDto;
import com.th.mux.model.Court;

public class CourtMapper {

    public static CourtDto toDto(Court court) {
        if (court == null) {
            return null;
        }
        return new CourtDto(court.getId(), court.getName());
    }

    public static Court toCourt(CourtDto courtDto) {
        if (courtDto == null) {
            return null;
        }
        Court court = new Court();
        court.setId(courtDto.getId());
        court.setName(courtDto.getName());
        return court;
    }
}
