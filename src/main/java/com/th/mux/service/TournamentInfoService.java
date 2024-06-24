package com.th.mux.service;

import com.th.mux.dto.TournamentInfoDto;
import com.th.mux.mapper.TournamentInfoMapper;
import com.th.mux.model.TournamentInfo;
import com.th.mux.repository.TournamentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentInfoService {
    private final TournamentInfoRepository tournamentInfoRepository;

    @Autowired
    public TournamentInfoService(TournamentInfoRepository tournamentInfoRepository) {
        this.tournamentInfoRepository = tournamentInfoRepository;
    }

    public TournamentInfoDto getTournamentInfo() {
        return tournamentInfoRepository.getLatestTournamentInfo().map(TournamentInfoMapper::toDto).orElse(null);
    }

    public TournamentInfoDto addTournamentInfo(TournamentInfoDto infoDto) {
        if (infoDto == null) {
            throw new RuntimeException("Information is invalid");
        }
        if (infoDto.getDateEnd().isBefore(infoDto.getDateStart()) ||
                infoDto.getDateEnd().isEqual(infoDto.getDateStart())) {
            throw new RuntimeException("Information is invalid");
        }
        TournamentInfo newInfo = new TournamentInfo();
        newInfo.setTitle(infoDto.getTitle());
        newInfo.setDateStart(infoDto.getDateStart());
        newInfo.setDateEnd(infoDto.getDateEnd());
        newInfo.setDescription(infoDto.getDescription());
        return TournamentInfoMapper.toDto(tournamentInfoRepository.save(newInfo));
    }
}
