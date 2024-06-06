package com.th.mux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class StatisticDto {
    private int id;
    @JsonProperty("mitarbeiter_id")
    private int userId;
    @JsonProperty("datum")
    private LocalDate date;
    @JsonProperty("schritte")
    private int steps;
    @JsonProperty("strecke")
    private double distance;
}
