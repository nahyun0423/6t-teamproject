package com.team6.routineapp.dto

import com.google.gson.annotations.SerializedName

data class ExerciseDTO(
    @SerializedName("exerciseName")
    var exerciseName: String? = null,

    @SerializedName("descriptions")
    var descriptions: String? = null,

    @SerializedName("videoLink")
    var videoLink: String? = null,

    @SerializedName("target")
    var target: String? = null,

    @SerializedName("tools")
    var tools: String? = null
)