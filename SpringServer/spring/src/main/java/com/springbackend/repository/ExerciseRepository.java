package com.springbackend.repository;
import com.springbackend.entity.Exercise;
import com.springbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

}
