package com.springbackend.controller;

import com.springbackend.dto.ExerciseDTO;
import com.springbackend.entity.Exercise;
import com.springbackend.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExerciseController {
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }


    //DB에서 운동정보 호출
    @GetMapping("/exercises/{exerciseName}")
    public ExerciseDTO getInfo(@PathVariable String exerciseName){
        return exerciseService.getExercise(exerciseName);
    }

    //모든 운동정보 호출
    @GetMapping("/exercises")
    public List<ExerciseDTO> getAllExercises() {
        return exerciseService.getAllExercises();
    }

}
