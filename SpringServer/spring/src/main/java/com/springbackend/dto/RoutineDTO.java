package com.springbackend.dto;

import com.springbackend.entity.Routine;
import com.springbackend.entity.RoutineDetail;
import com.springbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoutineDTO {
    private Integer routineId;
    private String userId;
    private String routineName;
    private Set<RoutineDetailDTO> routineDetails;

    public RoutineDTO(Routine routine) {
        this.userId = routine.getUser().getUserId();
        this.routineName = routine.getRoutineName();
        this.routineDetails = routine.getRoutineDetails().stream()
                .map(RoutineDetailDTO::new)
                .collect(Collectors.toSet());
    }

    public RoutineDTO(Routine routine, Set<RoutineDetail> routineDetails) {
        this.userId = routine.getUser().getUserId();
        this.routineName = routine.getRoutineName();
        this.routineDetails = routineDetails.stream().map(RoutineDetailDTO::new)
                .collect(Collectors.toSet());
    }

    public Routine toEntity(User user, Set<RoutineDetail> routineDetails) {
        return Routine.builder()
                .user(user)
                .routineName(routineName)
                .routineDetails(routineDetails)
                .build();
    }
}