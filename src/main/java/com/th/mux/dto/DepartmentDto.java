package com.th.mux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class DepartmentDto {
    private long id;
    private String name;
    @JsonProperty("gericht_id")
    private long courtId;

}
