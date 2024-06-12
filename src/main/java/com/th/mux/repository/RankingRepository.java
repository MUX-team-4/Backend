package com.th.mux.repository;

import com.th.mux.dto.TimePeriodDto;
import com.th.mux.model.Department;
import com.th.mux.model.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {
    //@Query("SELECT new com.th.mux.dto.RankingByDepDto(r.department.id, r.department.name, count(r.steps)) FROM Ranking r GROUP BY r.department")
    /**
     * Object[0] = department.id, Object[1] = steps;
     */
    @Query("SELECT r.department.id, sum(r.steps) FROM Ranking r GROUP BY r.department.id")
    public List<Object[]> getRankings();
    @Query("SELECT r.department.id, sum(r.steps) FROM Ranking r WHERE r.date BETWEEN ?1 AND ?2 GROUP BY r.department.id")
    public List<Object[]> getRankingsByTimePeriod(LocalDate fromDate, LocalDate toDate);
    @Query("SELECT r FROM Ranking r WHERE r.department = ?1 AND r.date = ?2")
    public Optional<Ranking> findRankingByIdAndDate(Department department, LocalDate date);

}
