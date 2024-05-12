package com.springbackend.dto;

import com.springbackend.entity.Exercise;
import com.springbackend.entity.Routine;
import com.springbackend.entity.RoutineDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoutineDetailDTO {

    private Integer routineDetailId;
    private Integer routineSequence;
    private Routine routine;
    private Exercise exercise;
    private Integer sets;
    private Integer reps;

    public RoutineDetailDTO(RoutineDetail routineDetail) {
        this.routineDetailId = routineDetail.getRoutineDetailId();
        this.routineSequence = routineDetail.getRoutineSequence();
        this.routine = routineDetail.getRoutine();
        this.exercise = routineDetail.getExercise();
        this.sets = routineDetail.getSets();
        this.reps = routineDetail.getReps();
    }

    public RoutineDetail toEntity(){
        return RoutineDetail.builder()
                .routineDetailId(routineDetailId)
                .routineSequence(routineSequence)
                .routine(routine)
                .exercise(exercise)
                .sets(sets)
                .reps(reps)
                .build();
    }
}
