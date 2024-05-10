package com.springbackend.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "exercises")
public class Exercise {
    @Id
    @Column(name = "ExerciseID")
    private Integer exerciseId;

    @Column(name = "ExerciseName")
    private String exerciseName;

    @Column(name = "Descriptions", columnDefinition = "TEXT")
    private String descriptions;

    @Column(name = "VideoLink", columnDefinition = "TEXT")
    private String videoLink;

}