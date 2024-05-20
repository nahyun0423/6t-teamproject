package com.team6.routineapp.dto

import com.google.gson.annotations.SerializedName

data class RoutineDetailDTO(
    @SerializedName("exerciseName")
    var exerciseName: String? = null,

    @SerializedName("sets")
    var sets: Int? = null,

    @SerializedName("reps")
    var reps: Int? = null,

    @SerializedName("weight")
    var weight: Int? = null
)