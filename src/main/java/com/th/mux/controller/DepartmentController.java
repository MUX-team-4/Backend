package com.th.mux.controller;

import com.th.mux.dto.DepartmentDto;
import com.th.mux.mapper.DepartmentMapper;
import com.th.mux.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/dienstellen")
public class DepartmentController {
    private final DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartment() {
        return ResponseEntity.ok(departmentService.getDepartments());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable(name = "id") long departmentId) {
        return ResponseEntity.ok(departmentService.getDepartment(departmentId));
    }
}
