package com.th.mux.controller;

import com.th.mux.dto.RankingDto;
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
     * Get all ranking
     * @return
     */
    @GetMapping()
    ResponseEntity<List<RankingDto>> getRanking() {
        return ResponseEntity.ok(rankingService.getRankings().stream().map(RankingMapper::toDto)
                .collect(Collectors.toList()));
    }

    /**
     * Get all ranking in current month
     * @return
     */
    @GetMapping("/current-month")
    ResponseEntity<List<RankingDto>> getRankingByCurrentMonth() {
        return ResponseEntity.ok(rankingService.getRankings().stream().map(RankingMapper::toDto)
                .collect(Collectors.toList()));
    }

}
