package com.th.mux.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "mitarbeiter")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50)
    private String name;
    @Column(length = 100)
    private String email;
    @Column(name = "rolle", length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;
    // in database with BCryptPasswordEncoder
    @Column(name = "passwort", length = 100)
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Statistic> statistics;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dienstelle_id", nullable = false) //foreign key
    private Department department;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;
}