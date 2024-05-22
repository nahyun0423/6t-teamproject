package com.springbackend.impl;

import com.springbackend.dto.ExerciseDTO;
import com.springbackend.entity.Exercise;
import com.springbackend.repository.ExerciseRepository;
import com.springbackend.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public ExerciseDTO getExercise(String exerciseName) {
        Optional<Exercise> exercise = exerciseRepository.findById(exerciseName);
        return exercise.map(ExerciseDTO::new).orElse(null);
    }
}
