package com.th.mux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TournamentInfoDto {
    @JsonProperty("titel")
    private String title;
    @JsonProperty("datum_beginn")
    private LocalDate dateStart;
    @JsonProperty("datum_ende")
    private LocalDate dateEnd;
    @JsonProperty("beschreibung")
    private String description;
}
