package com.springbackend.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "routineDetails")
public class RoutineDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoutineDetailID")
    private Integer routineDetailId;

    @ManyToOne
    @JoinColumn(name = "RoutineName", referencedColumnName = "RoutineName")
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "ExerciseName", referencedColumnName = "ExerciseName")
    private Exercise exercise;

    @Column(name = "Sets")
    private Integer sets;

    @Column(name = "Reps")
    private Integer reps;

    @Column(name = "Weight")
    private Integer weight;


    @Builder
    public RoutineDetail(Integer routineDetailId, Routine routine, Exercise exercise, Integer sets, Integer reps, Integer weight) {
        this.routineDetailId = routineDetailId;
        this.routine = routine;
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.weight=weight;
    }
}
