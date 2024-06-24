package com.th.mux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileDto {
    @JsonProperty("tagesziel")
    private int dailyGoal;
    @JsonProperty("koerpergroesse")
    private int height;
    @JsonProperty("schrittlaenge")
    private int stepLength;
}
