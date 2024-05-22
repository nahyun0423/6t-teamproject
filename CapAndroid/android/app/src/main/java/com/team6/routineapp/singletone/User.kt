package com.team6.routineapp.singletone

import com.team6.routineapp.dto.UserDTO

object User {
    var currentUser: UserDTO? = null

    fun saveUser(user: UserDTO) {
        currentUser = user
    }

    fun logoutUser() {
        currentUser = null
    }

    fun isLoggedIn(): Boolean = currentUser != null
}