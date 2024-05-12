package com.springbackend.impl;

import com.springbackend.dto.RoutineDTO;
import com.springbackend.entity.Routine;
import com.springbackend.repository.RoutineRepository;
import com.springbackend.service.RoutineService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoutineServiceImpl implements RoutineService {

    private final RoutineRepository routineRepository;

    public RoutineServiceImpl(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    @Override
    public RoutineDTO getRoutine(int routineId) {
        Optional<Routine> routine = routineRepository.findById(routineId);
        return routine.map(RoutineDTO::new).orElse(null);
    }
}
