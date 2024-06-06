package com.th.mux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RankingDto {
    private int id;
    @JsonProperty("dienstelle_id")
    private int departmentId;
    @JsonProperty("gesamt")
    private int steps;
    @JsonProperty("datum")
    private LocalDate date;
}
