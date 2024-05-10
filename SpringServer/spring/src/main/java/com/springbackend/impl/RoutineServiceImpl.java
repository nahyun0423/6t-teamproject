package com.springbackend.impl;

import com.springbackend.entity.Routine;
import com.springbackend.repository.RoutineRepository;
import com.springbackend.service.RoutineService;
import org.springframework.stereotype.Service;

@Service
public class RoutineServiceImpl implements RoutineService {

    private final RoutineRepository routineRepository;

    public RoutineServiceImpl(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    @Override
    public Routine getRoutine(int routineId) {
        return routineRepository.findById(routineId).orElse(null);
    }
}
