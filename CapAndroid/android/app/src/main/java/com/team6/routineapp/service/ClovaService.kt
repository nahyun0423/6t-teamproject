package com.team6.routineapp.service

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ClovaService {
    //POST방식일 시 필요
    @FormUrlEncoded
    @POST("clova")
    fun postClovaResponse(@Field("query") query: String): Call<String>
}