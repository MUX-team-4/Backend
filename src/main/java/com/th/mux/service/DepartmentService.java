package com.th.mux.service;

import com.th.mux.dto.DepartmentDto;
import com.th.mux.mapper.DepartmentMapper;
import com.th.mux.model.Department;
import com.th.mux.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDto> getDepartments() {
        return departmentRepository.findAll().stream().map(DepartmentMapper::toDto)
                .collect(Collectors.toList());
    }
}
