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
        "덤벨 컬" -> R.drawable.dumbbell_curl
        "바벨 로우" -> R.drawable.barbell_row
        "바벨 컬" -> R.drawable.barbell_curl
        "벤치 프레스" -> R.drawable.bench_press
        "바이셉스 컬 머신" -> R.drawable.biceps_curl_machine
        "바이시클 크런치" -> R.drawable.bicycle_crunch
        "케이블 크로스오버" -> R.drawable.cable_crossover
        "케이블 원 암 리버스 플라이" -> R.drawable.cable_one_arm_reverse_fly
        "케이블 오버헤드 컬" -> R.drawable.cable_overhead_curl
        "케이블 시티드 로우" -> R.drawable.cable_seated_row
        "체스트 프레스" -> R.drawable.chest_press
        "컨벤셔널 데드리프트" -> R.drawable.conventional_deadlift
        "디클라인 벤치 프레스" -> R.drawable.decline_bench_press
        "디클라인 크런치" -> R.drawable.decline_crunch
        "디클라인 니 레이즈" -> R.drawable.decline_knee_raise
        "딥스" -> R.drawable.dips
        "덤벨 컬" -> R.drawable.dumbbell_curl
        "덤벨 플라이" -> R.drawable.dumbbell_fly
        "덤벨 레터럴 레이즈" -> R.drawable.dumbbell_lateral_raise
        "행잉 레그 레이즈" -> R.drawable.hanging_leg_raise_icon
        "힙 어덕션" -> R.drawable.hip_adduction
        "인클라인 벤치 프레스" -> R.drawable.incline_bench_press
        "케틀벨 스트릭트 프레스" -> R.drawable.kettlebell_strict_press
        "랫 풀 다운" -> R.drawable.lat_pull_down
        "레그 컬" -> R.drawable.leg_curl
        "레그 익스텐션" -> R.drawable.leg_extension
        "레그 프레스" -> R.drawable.leg_press
        "런지" -> R.drawable.lunge
        "라잉 트라이셉스 익스텐션" -> R.drawable.lying_triceps_extension

        "원 암 덤벨 로우" -> R.drawable.one_arm_dumbbell_row
        "오버헤드 프레스" -> R.drawable.overhead_press
        "파이크 푸시 업" -> R.drawable.pike_push_up
        "플랭크" -> R.drawable.plank
        "풀 업" -> R.drawable.pull_up
        "푸시 업" -> R.drawable.push_up
        "로매니안 데드리프트" -> R.drawable.romanian_deadlift
        "러시안 트위스트" -> R.drawable.russian_twist
        "숄더 프레스 머신" -> R.drawable.shoulder_press_machine
        "사이드 플랭크" -> R.drawable.side_plank
        "싯 업" -> R.drawable.sit_up
        "스미스 슈러그" -> R.drawable.smith_shrug
        "스쿼트" -> R.drawable.squat
        "트라이셉스 푸시다운" -> R.drawable.triceps_pushdown
        "브이 스쿼트" -> R.drawable.v_squat
        "리스트 롤러" -> R.drawable.wrist_roller
        else -> R.drawable.overhead_press
    }
}

/* Exercise를 중량이 포함된 것과 아닌 것으로 분류하는 함수 */
fun getCategory(exercise: Exercise): String {
    return when (exercise.tool) {
        "맨몸" -> "Training"
        "철봉" -> "Training"
        "바벨" -> "Weight Training"
        "덤벨" -> "Weight Training"
        else -> "Weight Training"
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