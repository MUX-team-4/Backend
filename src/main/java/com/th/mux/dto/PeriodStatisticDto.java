package com.th.mux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PeriodStatisticDto {
    @JsonProperty("mitarbeiter_id")

    private long userId;
    @JsonProperty("schritte")
    private long steps;
    @JsonProperty("strecke")
    private double distance;
    @JsonProperty("von_datum")
    private LocalDate fromDate;
    @JsonProperty("bis_datum")
    private LocalDate toDate;
}
