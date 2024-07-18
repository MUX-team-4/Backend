package com.th.mux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDto {
    private long id;
    @JsonProperty("mitarbeiter_id")
    private long userId;
    @JsonProperty("schritte")
    private long steps;
    @JsonProperty("strecke")
    private double distance;
    @JsonProperty("datum")
    private LocalDate date;
    @JsonProperty("name")
    private String name;

}
