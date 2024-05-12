package com.springbackend.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
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

    @Builder
    public Routine(Integer routineId, User user, String routineName, Set<RoutineDetail> routineDetails) {
        this.routineId = routineId;
        this.user = user;
        this.routineName = routineName;
        this.routineDetails = routineDetails;
    }
}