package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.semantics.Role.Companion.Button

class DislikeexerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dislikeexercise) //xml과 연결

        val finishbutton = findViewById<Button>(R.id.button)

    }
}