package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.team6.routineapp.fitness.Routine
import com.team6.routineapp.fitness.Training
import com.team6.routineapp.utility.convertFromDpToPx
import com.team6.routineapp.utility.getClassExtra
import com.team6.routineapp.utility.getImageResource


class RoutineInformationActivity : AppCompatActivity() {
    private lateinit var intentToExerciseInformationActivity: Intent
    private lateinit var intentToExerciseActivity: Intent
    private lateinit var trainingsLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_information)

        /* Declare */
        val routine = intent.getClassExtra("routine", Routine::class.java)

        val nameTextView: TextView = findViewById(R.id.activity_routine_information_textview_name)

        val closeButton: Button = findViewById(R.id.activity_routine_information_button_close)
        val startExerciseButton: Button =
            findViewById(R.id.activity_routine_information_button_start_exercise)

        /* Assign */
        intentToExerciseInformationActivity = Intent(this, ExerciseInformationActivity::class.java)
        intentToExerciseActivity = Intent(this, ExerciseActivity::class.java)
        trainingsLayout = findViewById(R.id.activity_routine_information_layout_trainings)

        nameTextView.text = routine!!.name

        generateTrainingsView(routine.trainings)

        closeButton.setOnClickListener {
            finish()
        }

        startExerciseButton.setOnClickListener {
            finish()

            intentToExerciseActivity.putExtra("routine", routine)
            startActivity(intentToExerciseActivity)
        }

    }

    /* Training에 대응되는 View 만듦 */
    private fun generateTrainingView(training: Training): View {
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

    /* Training List에 대응되는 View 만듦 */
    private fun generateTrainingsView(trainings: Array<Training?>) {
        val blankSpaceLayout = RelativeLayout(this)

        blankSpaceLayout.layoutParams = RelativeLayout.LayoutParams(convertFromDpToPx(80), convertFromDpToPx(210))

        for (training in trainings) trainingsLayout.addView(generateTrainingView(training!!))
        trainingsLayout.addView(blankSpaceLayout)
    }
}