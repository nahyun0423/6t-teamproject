package com.springbackend.controller;

import com.springbackend.dto.ExerciseDTO;
import com.springbackend.entity.Exercise;
import com.springbackend.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExerciseController {
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercises/{exerciseId}")
    public ExerciseDTO getInfo(@PathVariable int exerciseId){
        return exerciseService.getExercise(exerciseId);

    }
}
