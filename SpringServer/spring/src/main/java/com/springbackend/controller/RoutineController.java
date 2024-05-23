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

    //DB에 루틴 저장
    @PostMapping("/routine/save")
    public void saveRoutine(@RequestBody RoutineDTO routineDTO) {
        routineService.saveRoutine(routineDTO);
    }

    //루틴 이름으로 해당 루틴 정보 DB에서 호출
    @GetMapping("/routine/{routineName}")
    public RoutineDTO getRoutine(@PathVariable String routineName){
        return routineService.getRoutine(routineName);
    }

    //해당 유저가 생성한 모든 루틴 출력
    @GetMapping("/routine/all/{userId}")
    public List<RoutineDTO> getAllRoutine(@PathVariable String userId){
        return routineService.getAllRoutinesByUser(userId);
    }


    //test용
    @PostMapping("/routine")
    public RoutineDTO pushRoutine(@RequestBody RoutineDTO routineDTO) {
        return routineDTO;
    }


}
