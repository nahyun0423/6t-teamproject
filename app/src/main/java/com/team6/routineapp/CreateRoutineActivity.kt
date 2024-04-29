package com.team6.routineapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CreateRoutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_routine)

        val next = Intent(this, PreferexerciseActivity::class.java)
        val buttonToRecommendationResult = findViewById<Button>(R.id.buttonGetRecommendationFromAI)
        buttonToRecommendationResult.setOnClickListener{
            startActivity(next)
        }
    }
}