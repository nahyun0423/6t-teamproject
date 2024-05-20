package com.springbackend.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "exercises")
public class Exercise {

    @Id
    @Column(name = "ExerciseName")
    private String exerciseName;

    @Column(name = "Descriptions", columnDefinition = "TEXT")
    private String descriptions;

    @Column(name = "VideoLink", columnDefinition = "TEXT")
    private String videoLink;
    @Builder
    public Exercise(Integer exerciseId, String exerciseName, String descriptions, String videoLink) {
        this.exerciseName = exerciseName;
        this.descriptions = descriptions;
        this.videoLink = videoLink;
    }
}