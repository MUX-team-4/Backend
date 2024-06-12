package com.th.mux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class StatisticDto {
    private long id;
    @JsonProperty("mitarbeiter_id")
    private long userId;
    @JsonProperty("datum")
    private LocalDate date;
    @JsonProperty("schritte")
    private long steps;
    @JsonProperty("strecke")
    private double distance;
}
