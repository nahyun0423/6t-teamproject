package com.springbackend.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "UserID")
    private String userId;

    @Column(name = "Password")
    private String password;

    @Column(name = "Height")
    private Float height;

    @Column(name = "Weight")
    private Float weight;

    @Column(name = "MuscleMass")
    private Float muscleMass;



    @Column(name = "FatMass")
    private Float fatMass;
    @Column(name = "Gender")
    private String gender;

    @Column(name = "Shape")
    private String shape;
    @Column(name = "RM_Bench")
    private Integer RM_bench;

    @Column(name = "RM_Squat")
    private Integer RM_squat;

    @Builder
    public User(String userId, String password, Float height, Float weight, Float muscleMass, Float fatMass, String gender, String shape, Integer RM_bench, Integer RM_squat) {
        this.userId = userId;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.muscleMass = muscleMass;
        this.fatMass = fatMass;
        this.gender = gender;
        this.shape = shape;
        this.RM_bench = RM_bench;
        this.RM_squat = RM_squat;
    }

}