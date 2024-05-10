package com.springbackend.service;

import com.springbackend.entity.Exercise;
import org.springframework.stereotype.Service;

@Service
public interface ExerciseService {
    Exercise getExercise(int exerciseId);
}
