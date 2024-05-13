package com.springbackend.repository;
import com.springbackend.entity.Routine;
import com.springbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoutineRepository extends JpaRepository<Routine, String> {
    Optional<Routine> findByRoutineName(String routineName);
    List<Routine> findByUserUserId(String userId);
}
