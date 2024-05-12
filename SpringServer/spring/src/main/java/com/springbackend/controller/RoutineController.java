package com.springbackend.controller;

import com.springbackend.dto.RoutineDTO;
import com.springbackend.entity.Routine;
import com.springbackend.service.RoutineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoutineController {
    private final RoutineService routineService;

    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @GetMapping("users/{userName}/{routineName}")
    public RoutineDTO getRoutine(@PathVariable int routineId){
        return routineService.getRoutine(routineId);
    }
}
