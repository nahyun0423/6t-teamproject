package com.team6.routineapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.team6.routineapp.fitness.Exercise

class ExerciseInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_information) //xml과 연결

        val intent = intent
        val exercise = intent.getSerializableExtra("exercise") as Exercise

        findViewById<TextView>(R.id.activity_exercise_information_textview_exercise_name).setText(
            exercise.name
        )
        findViewById<TextView>(R.id.activity_exercise_information_textview_exercise_part).setText(
            String.format("전반적인 %s 자극", exercise.part)
        )

    }
}