package com.th.mux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * This class correspond to Entity Ranking
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RankingDto {
    private long id;
    @JsonProperty("dienstelle_id")
    private long departmentId;
    @JsonProperty("dienstelle_name")
    private String departmentName;
    @JsonProperty("gesamt")
    private long steps;
    // record by date
    @JsonProperty("datum")
    private LocalDate date;
}
