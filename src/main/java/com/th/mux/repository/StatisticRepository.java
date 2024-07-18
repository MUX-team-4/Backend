package com.th.mux.repository;

import com.th.mux.dto.StatisticDto;
import com.th.mux.dto.TimePeriodDto;
import com.th.mux.model.Statistic;
import com.th.mux.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
   //@Query("SELECT s FROM Statistic s WHERE s.user = ?1 AND s.date = ?2")
   @Query("SELECT s FROM Statistic s WHERE s.user = ?1 AND s.date = ?2")
    Optional<Statistic> findByIdAndDate(User user, LocalDate date);

    @Query("SELECT s FROM Statistic s WHERE s.user.id = ?1")
    Optional<List<Statistic>> findByUserId(long userId);

   @Query("SELECT s FROM Statistic s WHERE s.user.id = ?1 AND s.date BETWEEN ?2 AND ?3")
   Optional<List<Statistic>> findByUserIdAndTimePeriod(long userId, LocalDate fromDate, LocalDate toDate);

    @Query("SELECT s.user.id, sum(s.steps), sum(s.distance) FROM Statistic s GROUP BY s.user.id")
   Optional<List<Object[]>>getStatisticGroupByUser();
    @Query("SELECT s.user.id, sum(s.steps), sum(s.distance), s.user.name FROM Statistic s WHERE s.date BETWEEN ?1 AND ?2 GROUP BY s.user.id, s.user.name")
    Optional<List<Object[]>>getStatisticGroupByUserAndTimePeriod(LocalDate fromDate, LocalDate toDate);
}
