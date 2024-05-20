package com.springbackend.dto;

import com.springbackend.entity.Exercise;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDTO {
    private String exerciseName;
    private String descriptions;
    private String videoLink;

    public ExerciseDTO(Exercise exercise) {
        this.exerciseName = exercise.getExerciseName();
        this.descriptions = exercise.getDescriptions();
        this.videoLink = exercise.getVideoLink();
    }

    public Exercise toEntity(){
        return Exercise.builder()
                .exerciseName(exerciseName)
                .descriptions(descriptions)
                .videoLink(videoLink)
                .build();
    }

}
