package com.springbackend.dto;


import com.springbackend.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userId;
    private String password;
    private Float height;
    private Float weight;
    private Float muscleMass;
    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.height = user.getHeight();
        this.weight = user.getWeight();
        this.muscleMass = user.getMuscleMass();
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .height(height)
                .weight(weight)
                .muscleMass(muscleMass)
                .build();
    }


}
