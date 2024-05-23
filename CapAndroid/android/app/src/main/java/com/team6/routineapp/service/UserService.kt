package com.team6.routineapp.service

import com.team6.routineapp.dto.UserDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @POST("/signUp")
    fun signUp(@Body userDTO: UserDTO): Call<String>

    @GET("/login/{userId}/{password}")
    fun login(@Path("userId") userId: String, @Path("password") password: String): Call<UserDTO>

    @POST("/editUser")
    fun editUser(@Body userDTO: UserDTO): Call<UserDTO>
}