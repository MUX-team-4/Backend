package com.th.mux.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "ranking")
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "datum", nullable = false)
    private LocalDate date;
    @Column(name = "gesamt")
    // total steps
    private long steps;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dienstelle_id", nullable = false) //foreign key
    //@JoinColumn(name = "dienstelle_id", referencedColumnName = "id", nullable = false)
    private Department department;
}
