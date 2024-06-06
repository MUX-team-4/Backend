package com.th.mux.controller;

import com.th.mux.dto.CourtDto;
import com.th.mux.mapper.CourtMapper;
import com.th.mux.model.Court;
import com.th.mux.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gerichte")
public class CourtController {
    private final CourtService courtService;

    @Autowired
    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping
    ResponseEntity<List<CourtDto>> getCourts() {
        return ResponseEntity.ok(courtService.getCourts());
    }
}
