package com.th.mux.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "profil")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "tagesziel")
    private int dailyGoal;
    @Column(name = "koerpergroesse")
    private int height;
    @Column(name = "schrittlaenge")
    private int stepLength;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mitarbeiter_id",  nullable = false)
    private User user;
}
