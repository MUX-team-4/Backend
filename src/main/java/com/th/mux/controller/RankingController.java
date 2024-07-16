package com.th.mux.controller;

import com.th.mux.dto.RankingDto;
import com.th.mux.dto.TimePeriodDto;
import com.th.mux.mapper.RankingMapper;
import com.th.mux.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {
    private final RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    /**
     * Get rankings group by department
     * @return
     */
    @GetMapping()
    ResponseEntity<List<RankingDto>> getRankingsGroupByDepartment() {
        return ResponseEntity.ok(rankingService.getRankingsGroupByDepartment());
    }

    @PostMapping("/zeitraum")
    ResponseEntity<List<RankingDto>> getRankingsGroupByDepartment(@RequestBody TimePeriodDto timePeriodDto) {
        return ResponseEntity.ok(rankingService.getRankingsGroupByDepartment(timePeriodDto));
    }

    /**
     * Get all ranking in current month
     * @return
     */
//    @GetMapping("/aktueller-monat")
//    ResponseEntity<List<RankingDto>> getRankingByCurrentMonth() {
//        return ResponseEntity.ok(rankingService.getRankings().stream().map(RankingMapper::toDto)
//                .collect(Collectors.toList()));
//    }

}
