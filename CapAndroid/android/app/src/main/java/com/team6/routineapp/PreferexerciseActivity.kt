package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PreferexerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target_part) //xml과 연결

        val intent = Intent(this, DislikeexerciseActivity::class.java)
        val nextbutton = findViewById<Button>(R.id.prefer_exercise_button_next)
        nextbutton.setOnClickListener{
            startActivity(intent)
        }
    }
}