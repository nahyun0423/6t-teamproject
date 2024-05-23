package com.team6.routineapp.service

import com.team6.routineapp.dto.RoutineDTO
import retrofit2.Call
import retrofit2.http.*

interface RoutineService {
    @POST("routine/save")
    fun saveRoutine(@Body routineDTO: RoutineDTO): Call<Void>

    @GET("routine/{routineName}")
    fun getRoutine(@Path("routineName") routineName: String): Call<RoutineDTO>

    @GET("routine/all/{userId}")
    fun getAllRoutinesByUser(@Path("userId") userId: String): Call<List<RoutineDTO>>
}