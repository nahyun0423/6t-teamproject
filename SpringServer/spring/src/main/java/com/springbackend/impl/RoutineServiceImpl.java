package com.springbackend.impl;

import com.springbackend.dto.RoutineDTO;
import com.springbackend.entity.Exercise;
import com.springbackend.entity.Routine;
import com.springbackend.entity.RoutineDetail;
import com.springbackend.entity.User;
import com.springbackend.repository.ExerciseRepository;
import com.springbackend.repository.RoutineDetailRepository;
import com.springbackend.repository.RoutineRepository;
import com.springbackend.repository.UserRepository;
import com.springbackend.service.RoutineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoutineServiceImpl implements RoutineService {

    private final RoutineRepository routineRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final RoutineDetailRepository routineDetailRepository;

    public RoutineServiceImpl(RoutineRepository routineRepository, UserRepository userRepository, ExerciseRepository exerciseRepository, RoutineDetailRepository routineDetailRepository) {
        this.routineRepository = routineRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
        this.routineDetailRepository = routineDetailRepository;
    }


    @Override
    public void saveRoutine(RoutineDTO routineDTO) {
        User user = userRepository.findById(routineDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Routine routine = Routine.builder()
                .user(user)
                .routineName(routineDTO.getRoutineName())
                .build();
        routineRepository.save(routine);

        Set<RoutineDetail> routineDetails = routineDTO.getRoutineDetails().stream()
                .map(detailDto -> {
                    Exercise exercise = exerciseRepository.findById(detailDto.getExerciseName())
                            .orElseThrow(() -> new RuntimeException("Exercise not found"));
                    return RoutineDetail.builder()
                            .routine(routine) // 저장된 루틴 참조
                            .exercise(exercise) // 조회된 운동 참조
                            .sets(detailDto.getSets())
                            .reps(detailDto.getReps())
                            .weight(detailDto.getWeight())
                            .build();
                }).collect(Collectors.toSet());

        routine.setRoutineDetails(routineDetails);
        routineRepository.save(routine);
    }

    @Override
    public RoutineDTO getRoutine(String routineName) {
        Optional<Routine> routine = routineRepository.findByRoutineName(routineName);
        if (routine.isPresent()) {
            return new RoutineDTO(routine.get());
        } else {
            throw new RuntimeException("Routine not found with name: " + routineName);
        }
    }

    @Override
    public List<RoutineDTO> getAllRoutinesByUser(String userId) {
        List<Routine> routines = routineRepository.findByUserUserId(userId);
        if (!routines.isEmpty()) {
            return routines.stream()
                    .map(RoutineDTO::new)
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("No routines found for user: " + userId);
        }
    }

}