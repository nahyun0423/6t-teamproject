package com.team6.routineapp.service

import com.team6.routineapp.dto.RoutineDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ClovaService {
    @POST("clova_custom")
    fun getResponse(@Body data: String): Call<RoutineDTO>
}