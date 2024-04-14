package com.team6.routineapp.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ClovaService {
    @GET("clova")
    fun getClovaResponse(@Query("query") query: String): Call<String>
}