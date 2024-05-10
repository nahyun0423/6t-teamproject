package com.springbackend.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "routineDetails")
public class RoutineDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoutineDetailID")
    private Integer routineDetailId;

    @Column(name = "RoutineSequence")
    private Integer routineSequence;

    @ManyToOne
    @JoinColumn(name = "RoutineID")
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "ExerciseID")
    private Exercise exercise;

    @Column(name = "Sets")
    private Integer sets;

    @Column(name = "Reps")
    private Integer reps;

}
