package com.springbackend.dto;

import com.springbackend.entity.Routine;
import com.springbackend.entity.RoutineDetail;
import com.springbackend.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RoutineDTO {

    private Integer routineId;
    private User user;
    private String routineName;
    private Set<RoutineDetail> routineDetails;

    public RoutineDTO(Routine routine) {
        this.routineId = routine.getRoutineId();
        this.user = routine.getUser();
        this.routineName = routine.getRoutineName();
        this.routineDetails = routine.getRoutineDetails();
    }

    public Routine toEntity(){
        return Routine.builder().routineId(routineId)
                .user(user)
                .routineName(routineName)
                .routineDetails(routineDetails).build();
    }
}
