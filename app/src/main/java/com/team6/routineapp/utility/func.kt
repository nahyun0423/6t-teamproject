package com.team6.routineapp.utility

import android.content.Intent
import android.os.Build
import java.io.Serializable

fun <T : Serializable> Intent.getClassExtra(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, clazz)
    } else {
        this.getSerializableExtra(key) as T?
    }
}