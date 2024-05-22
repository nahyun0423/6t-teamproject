package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DislikeexerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_dislike_part) //xml과 연결

        val intent = Intent(this, RecommendationResultActivity::class.java)
        val buttonToRecommendationResult =
            findViewById<Button>(R.id.dislike_exercise_button_complete)
        buttonToRecommendationResult.setOnClickListener {
            startActivity(intent)
        }

    }
}