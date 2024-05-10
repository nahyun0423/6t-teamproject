package com.springbackend.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
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

}