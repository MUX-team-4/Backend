package com.th.mux.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "turnierinfo")
public class TournamentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "titel")
    private String title;
    @Column(name = "datum_beginn")
    private LocalDate dateStart;
    @Column(name = "datum_ende")
    private LocalDate dateEnd;
    @Column(name = "beschreibung")
    private String description;
}
