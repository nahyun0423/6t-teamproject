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

    private fun generateTrainingView(training: Training): View {
        val intentToExerciseInformationActivity =
            Intent(this, ExerciseInformationActivity::class.java)

        val trainingLayout: ConstraintLayout =
            layoutInflater.inflate(R.layout.view_training, null) as ConstraintLayout
        val trainingIconImageView =
            trainingLayout.findViewById<ImageView>(R.id.view_exercise_imageview)
        val trainingNameTextView =
            trainingLayout.findViewById<TextView>(R.id.view_exercise_textview_name)
        val trainingDetailTextView =
            trainingLayout.findViewById<TextView>(R.id.view_training_textview_detail)

        trainingLayout.setOnClickListener {
            intentToExerciseInformationActivity.putExtra("exercise", training.exercise)
            startActivity(intentToExerciseInformationActivity)
        }
        trainingIconImageView.setImageResource(getImageResource(training.exercise))
        trainingNameTextView.text = training.exercise.name
        trainingDetailTextView.text = training.getDetail()

        return trainingLayout
    }

    private fun generateTrainingsView(trainings: Array<Training?>) {
        val trainingsLayout = findViewById<LinearLayout>(R.id.layout_trainings)
        val blankSpaceLayout = RelativeLayout(this)

        for (training in trainings) trainingsLayout.addView(generateTrainingView(training!!))
        blankSpaceLayout.layoutParams =
            RelativeLayout.LayoutParams(convertFromDpToPx(80), convertFromDpToPx(210))

        trainingsLayout.addView(blankSpaceLayout)
    }
}