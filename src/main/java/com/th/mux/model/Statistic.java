package com.th.mux.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "statistik")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "datum", nullable = false)
    private LocalDate date;
    @Column(name = "schritte")
    private int steps;
    //private double kcal;
    @Column(name = "strecke")
    private double distance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mitarbeiter_id", nullable = false) //foreign key
    private User user;
}
