package com.springbackend.controller;

import com.springbackend.dto.RoutineDTO;
import com.springbackend.entity.Routine;
import com.springbackend.service.RoutineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoutineController {
    private final RoutineService routineService;

    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @PostMapping("/routine/save")
    public void saveRoutine(@RequestBody RoutineDTO routineDTO) {
        routineService.saveRoutine(routineDTO);
    }

    @PostMapping("/routine")
    public RoutineDTO pushRoutine(@RequestBody RoutineDTO routineDTO) {
        return routineDTO;
    }
    @GetMapping("/routine/{routineName}")
    public RoutineDTO getRoutine(@PathVariable String routineName){
        return routineService.getRoutine(routineName);
    }

    @GetMapping("/routine/all/{userId}")
    public List<RoutineDTO> getAllRoutine(@PathVariable String userId){
        return routineService.getAllRoutinesByUser(userId);
    }



}
