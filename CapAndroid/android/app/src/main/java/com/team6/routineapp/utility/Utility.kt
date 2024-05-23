package com.team6.routineapp.utility

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import com.team6.routineapp.R
import com.team6.routineapp.fitness.Exercise
import java.io.Serializable

/* Dp를 Px단위로 변경하는 함수 */
fun AppCompatActivity.convertFromDpToPx(value: Int): Int {
    var displayMetrics = resources.displayMetrics
    return Math.round(value * displayMetrics.density)
}

/* Exercise마다 이미지 리소스를 지정하는 함수 */
fun getImageResource(exercise: Exercise): Int {
    return when (exercise.name) {
        "오버헤드 프레스" -> R.drawable.overhead_press
        "행잉 레그 레이즈" -> R.drawable.hanging_leg_raise
        else -> 0
    }
}

/* Exercise를 중량이 포함된 것과 아닌 것으로 분류하는 함수 */
fun getCategory(exercise: Exercise): String {
    return when (exercise.tool) {
        "바벨" -> "Weight Training"
        else -> "Training"
    }
}

/* Intent로부터 Class 값을 가져오는 함수 */
fun <T : Serializable> Intent.getClassExtra(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, clazz)
    } else {
        this.getSerializableExtra(key) as T?
    }
}