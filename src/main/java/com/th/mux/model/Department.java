package com.th.mux.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "dienstelle")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 50)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gericht_id", nullable = false) //foreign key
    private Court court;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Ranking> ranking;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<User> users;
}
