package com.th.mux.service;

import com.th.mux.dto.CourtDto;
import com.th.mux.mapper.CourtMapper;
import com.th.mux.model.Court;
import com.th.mux.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourtService {

    private final CourtRepository courtRepository;

    @Autowired
    public CourtService(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    public List<CourtDto> getCourts() {
        return courtRepository.findAll().stream().map(CourtMapper::toDto)
                .collect(Collectors.toList());
    }
}
