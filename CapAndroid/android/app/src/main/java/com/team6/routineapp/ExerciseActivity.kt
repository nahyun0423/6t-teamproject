package com.team6.routineapp

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
}
