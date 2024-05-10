package com.springbackend.repository;
import com.springbackend.entity.Routine;
import com.springbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Integer> {

}
