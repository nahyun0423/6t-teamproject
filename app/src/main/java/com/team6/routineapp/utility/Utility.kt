package com.team6.routineapp.utility

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import com.team6.routineapp.R
import com.team6.routineapp.fitness.Exercise
import java.io.Serializable

fun AppCompatActivity.convertFromDpToPx(value: Int): Int {
    var displayMetrics = resources.displayMetrics
    return Math.round(value * displayMetrics.density)
}

fun getImageResource(exercise: Exercise): Int {
    return when (exercise.name) {
        "오버헤드 프레스" -> R.drawable.overhead_press
        "행잉 레그 레이즈" -> R.drawable.hanging_leg_raise
        else -> 0
    }
}

fun getImageResource(exerciseName: String): Int {
    return when (exerciseName) {
        "오버헤드 프레스" -> R.drawable.overhead_press
        "행잉 레그 레이즈" -> R.drawable.hanging_leg_raise
        else -> 0
    }
}

fun getCategory(exercise: Exercise): String {
    return when (exercise.tool) {
        "바벨" -> "Weight Training"
        else -> "Training"
    }
}

fun <T : Serializable> Intent.getClassExtra(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, clazz)
    } else {
        this.getSerializableExtra(key) as T?
    }
}