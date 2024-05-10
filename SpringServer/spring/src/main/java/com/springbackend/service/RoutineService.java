package com.springbackend.service;

import com.springbackend.entity.Routine;
import org.springframework.stereotype.Service;

@Service
public interface RoutineService {
    Routine getRoutine(int routineId);

}
