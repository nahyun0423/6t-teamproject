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

    @Builder
    public RoutineDetail(Integer routineDetailId, Integer routineSequence, Routine routine, Exercise exercise, Integer sets, Integer reps) {
        this.routineDetailId = routineDetailId;
        this.routineSequence = routineSequence;
        this.routine = routine;
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
    }
}
