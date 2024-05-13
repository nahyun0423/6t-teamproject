package com.springbackend.service;

import com.springbackend.dto.RoutineDTO;
import com.springbackend.entity.Routine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoutineService {
    RoutineDTO getRoutine(String routineName);
    void saveRoutine(RoutineDTO routineDTO);

    List<RoutineDTO> getAllRoutinesByUser(String userId);
}
