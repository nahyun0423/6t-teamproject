package com.team6.routineapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.team6.routineapp.fitness.Exercise

val overheadPress = Exercise("오버헤드 프레스", "팔", "바벨")
val hangingLegRaise = Exercise("행잉 레그 레이즈", "코어", "행잉 레그 레이즈 머신")
val exercises = arrayOf(overheadPress, hangingLegRaise)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}