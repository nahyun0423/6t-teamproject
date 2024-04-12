package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.semantics.Role.Companion.Button

class PreferexerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preferexercise) //xml과 연결

        val nextbutton = findViewById<Button>(R.id.button)

        val intent = Intent(this, DislikeexerciseActivity::class.java)
        nextbutton.setOnClickListener{
            startActivity(intent)
        }
    }
}