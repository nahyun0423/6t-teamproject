package com.team6.routineapp.dto

import com.google.gson.annotations.SerializedName

data class RoutineDTO(
    @SerializedName("routineId")
    var routineId: Int? = null,

    @SerializedName("userId")
    var userId: String? = null,

    @SerializedName("routineName")
    var routineName: String? = null,

    @SerializedName("routineDetails")
    var routineDetails: List<RoutineDetailDTO>? = null
)