package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.team6.routineapp.fitness.Routine
import com.team6.routineapp.fitness.Training
import com.team6.routineapp.utility.getClassExtra


class RoutineInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_information)

        val routine = intent.getClassExtra("routine", Routine::class.java)

        val intentToExerciseActivity = Intent(this, ExerciseActivity::class.java)

        val nameTextView: TextView = findViewById(R.id.activity_routine_information_textview_name)
        val closeButton: Button = findViewById(R.id.activity_routine_information_button_close)
        val startExerciseButton: Button =
            findViewById(R.id.activity_routine_information_button_start_exercise)

        nameTextView.text = routine!!.name
        closeButton.setOnClickListener {
            finish()
        }

        startExerciseButton.setOnClickListener {
            finish()

            intentToExerciseActivity.putExtra("routine", routine)
            startActivity(intentToExerciseActivity)
        }

    }
}