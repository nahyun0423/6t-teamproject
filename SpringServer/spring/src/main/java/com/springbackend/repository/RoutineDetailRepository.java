package com.springbackend.repository;
import com.springbackend.entity.Exercise;
import com.springbackend.entity.Routine;
import com.springbackend.entity.RoutineDetail;
import com.springbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RoutineDetailRepository extends JpaRepository<RoutineDetail, Integer> {
    Set<RoutineDetail> findByRoutine(Routine routine);

}
