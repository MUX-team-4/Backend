package com.th.mux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class PeriodStatisticDto {
    private long id;
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
