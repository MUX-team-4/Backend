package com.th.mux.controller;

import com.th.mux.dto.PeriodStatisticDto;
import com.th.mux.dto.StatisticDto;
import com.th.mux.dto.TimePeriodDto;
import com.th.mux.model.Statistic;
import com.th.mux.service.RankingService;
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
    private final RankingService rankingService;

    @Autowired
    public StatisticController(StatisticService statisticService, RankingService rankingService) {
        this.statisticService = statisticService;
        this.rankingService = rankingService;
    }

    @GetMapping("/dienstellen/{id}")
    public ResponseEntity<List<StatisticDto>> getStatisticsGroupByDepartment(@PathVariable(name = "id") long departmentId) {
        return ResponseEntity.ok(statisticService.getStatisticsGroupByDepartment(departmentId));
    }

    @GetMapping("/dienstellen/{id}/zeitraum")
    public ResponseEntity<List<StatisticDto>> getStatisticsGroupByDepartment(@PathVariable(name = "id") long departmentId,
                                                                             @RequestBody TimePeriodDto timePeriodDto) {
        return ResponseEntity.ok(statisticService.getStatisticGroupByUserAndTimePeriod(departmentId, timePeriodDto));
    }

    /**
     * Get all statistics of a user
     * @param userId
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<StatisticDto>> getStatistics(@PathVariable(name = "id") long userId) {
        return ResponseEntity.ok(statisticService.getStatistics(userId));
    }

    /**
     * Get all statistics of a user
     * @param userId
     * @return
     */
    @GetMapping("/{id}/zeitraum")
    public ResponseEntity<List<StatisticDto>> getStatistics(@PathVariable(name = "id") long userId, @RequestBody TimePeriodDto timePeriodDto) {
        return ResponseEntity.ok(statisticService.getStatistics(userId, timePeriodDto));
    }


    @GetMapping("/aktueller-monat/{id}")
    public ResponseEntity<List<StatisticDto>> getStatisticsByCurrentMonth(@PathVariable(name = "id") int userId) {
        return null;
    }

    @PutMapping()
    public ResponseEntity<StatisticDto> updateStatistic(@RequestBody StatisticDto dto) {
        log.info("input value StatisticDto = {}", dto);
        StatisticDto savedStatisticDto = statisticService.updateStatistic(dto);
        boolean resultUpdate = rankingService.updateRanking(savedStatisticDto);
        log.info("resultUpdate for Ranking = {}", resultUpdate);
        return ResponseEntity.ok(savedStatisticDto);
    }

    @PutMapping("/zeitraum")
    public ResponseEntity<List<PeriodStatisticDto>> updateStatistic() {
        return null;
    }

}
