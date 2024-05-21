package com.team6.routineapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.team6.routineapp.fitness.Exercise
import com.team6.routineapp.fitness.Training
import com.team6.routineapp.utility.convertFromDpToPx
import com.team6.routineapp.utility.getClassExtra
import com.team6.routineapp.utility.getImageResource

class ExerciseInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_information) //xml과 연결

        val exercise = intent.getClassExtra("exercise", Exercise::class.java) // 다른 Activity로부터 전달받은 Exercise를 받아 옴

        findViewById<TextView>(R.id.activity_exercise_information_textview_exercise_name).setText(
            exercise!!.name
        ) // 제목을 Exercise의 name으로 설정
        
        findViewById<TextView>(R.id.activity_exercise_information_textview_exercise_part).setText(
            String.format("전반적인 %s 자극", exercise.part)
        ) // 부제목을 Exercise의 part로 설정
    }
}