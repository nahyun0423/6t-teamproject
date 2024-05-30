package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.team6.routineapp.dto.RoutineDTO
import com.team6.routineapp.fitness.Exercise
import com.team6.routineapp.fitness.Routine
import com.team6.routineapp.fitness.Training
import com.team6.routineapp.fitness.WeightTraining
import com.team6.routineapp.utility.convertFromDpToPx
import com.team6.routineapp.utility.getClassExtra
import com.team6.routineapp.utility.getImageResource
import java.util.Timer
import java.util.TimerTask

class ExerciseActivity : AppCompatActivity() {
    private var startTime = 0L
    private var handler = Handler()
    private var runnable: Runnable? = null
    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val button: Button = findViewById(R.id.activity_exercise_button)

        val routine = intent.getClassExtra("routine", Routine::class.java)
        generateTrainingsView(routine!!.trainings)

        button.setOnClickListener {
            if (isRunning) {
                stopTimer()
            } else {
                startTimer()
            }
        }

        runnable = object : Runnable {
            override fun run() {
                val elapsedMillis = SystemClock.uptimeMillis() - startTime
                val seconds = (elapsedMillis / 1000).toInt() % 60
                val minutes = (elapsedMillis / (1000 * 60)).toInt() % 60
                val hours = (elapsedMillis / (1000 * 60 * 60)).toInt() % 24
                val timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                button.text = timeString
                handler.postDelayed(this, 1000)
            }
        }
    }

    private fun startTimer() {
        startTime = SystemClock.uptimeMillis()
        handler.postDelayed(runnable!!, 0)
        isRunning = true
    }

    private fun stopTimer() {
        handler.removeCallbacks(runnable!!)
        isRunning = false
    }

    /* Training에 대응되는 View 만듦 */
    private fun generateTrainingView(training: Training): View {
        val intentToExerciseInformationActivity = Intent(this, ExerciseInformationActivity::class.java)
        val trainingLayout: ConstraintLayout = layoutInflater.inflate(R.layout.view_training, null) as ConstraintLayout
        val trainingIconImageView = trainingLayout.findViewById<ImageView>(R.id.view_exercise_imageview)
        val trainingNameTextView = trainingLayout.findViewById<TextView>(R.id.view_exercise_textview_name)
        val trainingDetailTextView = trainingLayout.findViewById<TextView>(R.id.view_training_textview_detail)

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
        val trainingsLayout: LinearLayout = findViewById(R.id.activity_exercise_layout_trainings)

        for (training in trainings) trainingsLayout.addView(generateTrainingView(training!!))
    }
}
