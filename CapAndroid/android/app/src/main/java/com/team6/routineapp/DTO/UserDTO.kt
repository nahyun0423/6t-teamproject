package com.team6.routineapp.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("userId")
    var userId: String? = null,

    @SerializedName("password")
    var password: String? = null,

    @SerializedName("height")
    var height: Float? = null,

    @SerializedName("weight")
    var weight: Float? = null,

    @SerializedName("muscleMass")
    var muscleMass: Float? = null,

    @SerializedName("fatMass")
    var fatMass: Float? = null,

    @SerializedName("gender")
    var gender: String? = null,

    @SerializedName("shape")
    var shape: String? = null,

    @SerializedName("rm_bench")
    var rm_bench: Int? = null,

    @SerializedName("rm_squat")
    var rm_squat: Int? = null
)