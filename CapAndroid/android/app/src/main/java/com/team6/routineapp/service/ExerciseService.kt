package com.team6.routineapp.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExerciseService {
    @GET("exercises/{userId}")
    fun getUserById(@Path("userId") userId: Int): Call<String>

}