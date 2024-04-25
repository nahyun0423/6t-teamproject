package com.team6.routineapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    private val BASE_URL = "http://10.0.2.2:8080/"

    val instance: ClovaService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())  // JSON 처리를 위한 GsonConverterFactory 추가
            .build()
            .create(ClovaService::class.java)
    }
}