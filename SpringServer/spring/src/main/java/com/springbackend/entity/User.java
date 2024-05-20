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

    @Builder
    public User(String userId, String password, Float height, Float weight, Float muscleMass, String shape, Float fatMass, String gender) {
        this.userId = userId;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.muscleMass = muscleMass;
        this.shape = shape;
        this.fatMass = fatMass;
        this.gender = gender;
    }

}