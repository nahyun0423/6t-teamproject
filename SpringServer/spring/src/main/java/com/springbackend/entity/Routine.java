package com.springbackend.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "routines")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoutineID")
    private Integer routineId;

    @Column(name = "RoutineName")
    private String routineName;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoutineDetail> routineDetails;

    @Builder
    public Routine(User user, String routineName, Set<RoutineDetail> routineDetails) {
        this.user = user;
        this.routineName = routineName;
        this.routineDetails = routineDetails;
    }
}