package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.StringJoiner

class SelectPurposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_purpose)
        activityStack.push(this)

        val stringJoiner = StringJoiner(", ")
        val intentToSelectTargetPlaceActivity = Intent(this,SelectTargetPlaceActivity::class.java)
        val button: Button = findViewById(R.id.activity_select_purpose_button)
        val loseWeightCheckBox: CheckBox = findViewById(R.id.activity_select_purpose_checkbox_lose_weight)
        val hypertrophyCheckBox: CheckBox = findViewById(R.id.activity_select_purpose_checkbox_hypertrophy)
        val enduranceCheckBox: CheckBox = findViewById(R.id.activity_select_purpose_checkbox_endurance)
        val strengthCheckBox: CheckBox = findViewById(R.id.activity_select_purpose_checkbox_strength)

        button.setOnClickListener {
            if (loseWeightCheckBox.isChecked) stringJoiner.add("체지방 감소")
            else if (hypertrophyCheckBox.isChecked) stringJoiner.add("근비대")
            else if (enduranceCheckBox.isChecked) stringJoiner.add("근지구력 강화")
            else if (strengthCheckBox.isChecked) stringJoiner.add("근력 강화")

            intentToSelectTargetPlaceActivity.putExtra("purpose", stringJoiner.toString())
            intentToSelectTargetPlaceActivity.putExtra("part", intent.getStringExtra("part"))
            startActivity(intentToSelectTargetPlaceActivity)
        }
    }
}