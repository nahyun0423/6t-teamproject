package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class RoutineInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_information)
        findViewById<TextView>(R.id.activity_routine_information_textview_name).text = intent.getStringExtra("name")

        val button = findViewById<Button>(R.id.activity_routine_information_button)

        val intent = Intent(this, RoutineActivity::class.java)
        button.setOnClickListener(View.OnClickListener{
            startActivity(intent)
        })

    }
}