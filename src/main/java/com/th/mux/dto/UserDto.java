package com.th.mux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.th.mux.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String name;
    private String email;
    private Role role;
    @JsonProperty("dienstelle_id")
    private long departmentId;
}
