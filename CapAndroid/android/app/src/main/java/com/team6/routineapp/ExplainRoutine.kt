package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.semantics.Role.Companion.Button


class ExplainRoutine : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.explainroutine)
        findViewById<TextView>(R.id.textView3).text = intent.getStringExtra("name")

        val button = findViewById<Button>(R.id.button)

        val intent = Intent(this, RoutineList::class.java)
        button.setOnClickListener(View.OnClickListener{
            startActivity(intent)
        })

    }
}