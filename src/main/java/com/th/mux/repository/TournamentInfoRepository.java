package com.th.mux.repository;

import com.th.mux.model.TournamentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentInfoRepository extends JpaRepository<TournamentInfo, Long> {

    @Query("SELECT t FROM TournamentInfo t ORDER BY t.dateEnd DESC LIMIT 1")
    Optional<TournamentInfo> getLatestTournamentInfo();

}
