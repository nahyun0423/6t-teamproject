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

    @Column(name = "Target", columnDefinition = "TEXT")
    private String target;

    @Column(name = "Tools", columnDefinition = "TEXT")
    private String tools;
    @Builder
    public Exercise(String exerciseName, String descriptions, String videoLink, String target, String tools) {
        this.exerciseName = exerciseName;
        this.descriptions = descriptions;
        this.videoLink = videoLink;
        this.target = target;
        this.tools = tools;
    }
}