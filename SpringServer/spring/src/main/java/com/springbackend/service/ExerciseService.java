package com.springbackend.service;

import com.springbackend.dto.ExerciseDTO;
import com.springbackend.entity.Exercise;
import org.springframework.stereotype.Service;

@Service
public interface ExerciseService {
    ExerciseDTO getExercise(int exerciseId);
}
