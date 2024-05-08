package com.team6.routineapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.team6.routineapp.fitness.Exercise

class ExerciseInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_information) //xml과 연결

        val intent = getIntent()
        val exercise = intent.getSerializableExtra("exercise") as Exercise

        findViewById<TextView>(R.id.activityExerciseInformation_textViewExerciseName).setText(exercise.name)
        findViewById<TextView>(R.id.activityExerciseInformation_textViewExercisePart).setText(String.format("전반적인 %s 자극", exercise.part))

    }
}