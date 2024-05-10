package com.springbackend.impl;

import com.springbackend.entity.Exercise;
import com.springbackend.repository.ExerciseRepository;
import com.springbackend.service.ExerciseService;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Exercise getExercise(int exerciseId) {
        return exerciseRepository.findById(exerciseId).orElse(null);
    }
}
