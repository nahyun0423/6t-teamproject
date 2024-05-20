package com.team6.routineapp.service

import com.team6.routineapp.dto.ExerciseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExerciseService {
    @GET("exercises/{exerciseName}")
    fun getExercise(@Path("exerciseName") exerciseName: String): Call<ExerciseDTO>
}