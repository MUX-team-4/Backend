package com.th.mux.mapper;

import com.th.mux.dto.DepartmentDto;
import com.th.mux.model.Department;

public class DepartmentMapper {
    public static DepartmentDto toDto(Department department) {
        if (department == null) {
            throw new RuntimeException("Input is invalid");
        }
        return new DepartmentDto(department.getId(), department.getName(), department.getCourt().getId());
    }
}
