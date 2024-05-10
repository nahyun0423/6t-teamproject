package com.springbackend.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "routines")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoutineID")
    private Integer routineId;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @Column(name = "RoutineName")
    private String routineName;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoutineDetail> routineDetails;

}