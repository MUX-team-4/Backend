package com.th.mux.controller;

import com.th.mux.dto.TournamentInfoDto;
import com.th.mux.service.TournamentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/turnierinfo")
@Slf4j
public class TournamentInfoController {
    private final TournamentInfoService tournamentInfoService;

    @Autowired
    public TournamentInfoController(TournamentInfoService tournamentInfoService) {
        this.tournamentInfoService = tournamentInfoService;
    }

    /**
     * Get current tournament information
     * @return
     */
    @GetMapping
    public ResponseEntity<TournamentInfoDto> getTournamentInfo() {
        log.info("getTournamentInfo");
        return ResponseEntity.ok(tournamentInfoService.getTournamentInfo());
    }

    /**
     * Add new tournament information
     * @param infoDto
     * @return
     */
    @PutMapping
    public ResponseEntity<TournamentInfoDto> addTournamentInfo(@RequestBody TournamentInfoDto infoDto) {
        log.info("getTournamentInfo ={}", infoDto);
        return ResponseEntity.ok(tournamentInfoService.addTournamentInfo(infoDto));
    }
}
