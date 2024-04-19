package com.team6.routineapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChoiceTap : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choicetap)

        val intent = Intent(this, PreferexerciseActivity::class.java)
        val buttonToRecommendationResult = findViewById<Button>(R.id.buttonGettingRecommendationFromAI)
        buttonToRecommendationResult.setOnClickListener{
            startActivity(intent)
        }
    }
}