package com.team6.routineapp.singletone

import com.team6.routineapp.dto.ExerciseDTO

object Exercise {
    private val exercises: MutableList<ExerciseDTO> = mutableListOf()

    fun addExercise(exercise: ExerciseDTO) {
        exercises.add(exercise)
    }
    fun getExerciseByName(exerciseName: String): ExerciseDTO? {
        return exercises.find { it.exerciseName == exerciseName }
    }

    fun getAllExercise(): List<ExerciseDTO> {
        return exercises.toList()
    }
}