package com.th.mux.controller;

import com.th.mux.dto.StatisticDto;
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

    /**
     * Get all statistics of a user
     * @param userId
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<Statistic>> getStatistics(@RequestParam int userId) {
        return null;
    }


    @GetMapping("/aktueller-monat/{id}")
    public ResponseEntity<List<StatisticDto>> getStatisticsByCurrentMonth(@RequestParam int userId) {
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

}
