package com.springbackend.repository;
import com.springbackend.entity.RoutineDetail;
import com.springbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineDetailRepository extends JpaRepository<RoutineDetail, Integer> {

}
