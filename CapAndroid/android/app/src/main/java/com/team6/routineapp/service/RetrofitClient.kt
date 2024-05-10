package com.team6.routineapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    private val BASE_URL = "http://10.0.2.2:8080/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    //val instance: ClovaService = retrofit.create(ClovaService::class.java)
    val clovaService: ClovaService = retrofit.create(ClovaService::class.java)
    val exerciseService: ExerciseService = retrofit.create(ExerciseService::class.java)
}