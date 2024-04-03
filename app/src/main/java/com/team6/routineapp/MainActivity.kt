package com.team6.routineapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonToRecommendationResult = findViewById<Button>(R.id.buttonToRecommendationResult)

        buttonToRecommendationResult.setOnClickListener{
            val intent = Intent(this, RecommendationResultActivity::class.java)
            startActivity(intent)
        }
    }
}