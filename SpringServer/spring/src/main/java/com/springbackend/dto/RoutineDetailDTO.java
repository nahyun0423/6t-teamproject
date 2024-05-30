package com.springbackend.dto;

import com.springbackend.entity.Exercise;
import com.springbackend.entity.Routine;
import com.springbackend.entity.RoutineDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoutineDetailDTO {
    private Integer routineDetailId;
    private String exerciseName;
    private String routineName;
    private Integer sets;
    private Integer reps;
    private Integer weight;

    public RoutineDetailDTO(RoutineDetail routineDetail) {
        this.routineDetailId = routineDetail.getRoutineDetailId();
        this.exerciseName = routineDetail.getExercise().getExerciseName();
        this.routineName = routineDetail.getRoutine().getRoutineName();
        this.sets = routineDetail.getSets();
        this.reps = routineDetail.getReps();
        this.weight = routineDetail.getWeight();
    }

    public RoutineDetail toEntity(Exercise exercise, Routine routine) {
        return RoutineDetail.builder()
                .exercise(exercise)
                .routine(routine)
                .sets(sets)
                .reps(reps)
                .weight(weight)
                .build();
    }
}