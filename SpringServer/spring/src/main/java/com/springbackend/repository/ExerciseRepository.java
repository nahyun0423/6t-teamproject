package com.springbackend.repository;
import com.springbackend.entity.Exercise;
import com.springbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, String> {
    Optional<Exercise> findByExerciseName(String exerciseName);

}
