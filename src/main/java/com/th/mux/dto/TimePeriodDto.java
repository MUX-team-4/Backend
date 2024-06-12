package com.th.mux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TimePeriodDto {
    @JsonProperty("von_datum")
    private LocalDate fromDate;
    @JsonProperty("bis_datum")
    private LocalDate toDate;
}
