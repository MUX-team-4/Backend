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
    private int id;
    @Column(name = "datum", nullable = false)
    private LocalDate date;
    @Column(name = "gesamt")
    // total steps
    private int steps;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dienstelle_id", referencedColumnName = "id", nullable = false) // onetoone
    private Department department;
}
