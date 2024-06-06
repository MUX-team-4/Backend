package com.th.mux.controller;

import com.th.mux.dto.StatisticDto;
import com.th.mux.model.Statistic;
import com.th.mux.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistiken")
@Slf4j
public class StatisticController {

    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    /**
     * Get all statistics of a user
     * @param userId
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<Statistic>> getStatistics(@RequestParam int userId) {
        return null;
    }


    @GetMapping("/current-month/{id}")
    public ResponseEntity<List<StatisticDto>> getStatisticsByCurrentMonth(@RequestParam int userId) {
        return null;
    }

    @PutMapping()
    public ResponseEntity<StatisticDto> updateStatistic(@RequestBody StatisticDto dto) {
        return ResponseEntity.ok(statisticService.updateStatistic(dto));
    }

}
