package com.springbackend.dto;

import com.springbackend.entity.Exercise;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseDTO {

    private Integer exerciseId;
    private String exerciseName;
    private String descriptions;
    private String videoLink;

    public ExerciseDTO(Exercise exercise) {
        this.exerciseId = exercise.getExerciseId();
        this.exerciseName = exercise.getExerciseName();
        this.descriptions = exercise.getDescriptions();
        this.videoLink = exercise.getVideoLink();
    }

    public Exercise toEntity(){
        return Exercise.builder()
                .exerciseId(exerciseId)
                .exerciseName(exerciseName)
                .descriptions(descriptions)
                .videoLink(videoLink)
                .build();
    }

}
