package com.springbackend.service;

import com.springbackend.dto.RoutineDTO;
import com.springbackend.entity.Routine;
import org.springframework.stereotype.Service;

@Service
public interface RoutineService {
    RoutineDTO getRoutine(int routineId);

}
