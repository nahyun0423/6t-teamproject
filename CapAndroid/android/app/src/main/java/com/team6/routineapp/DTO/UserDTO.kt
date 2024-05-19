package com.team6.routineapp.dto

data class UserDTO(
    var userId: String? = null,
    var password: String? = null,
    var height: Float? = null,
    var weight: Float? = null,
    var muscleMass: Float? = null,
    var fatMass: Float? = null,
    var gender: String? = null,
    var shape: String? = null
)