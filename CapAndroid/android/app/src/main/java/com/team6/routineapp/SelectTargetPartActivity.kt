package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import java.util.StringJoiner

class SelectTargetPartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_target_part)
        activityStack.push(this)
        var stringJoiner = StringJoiner(", ")

        val intentToRecommendationResultActivity = Intent(this, RecommendationResultActivity::class.java)
        val intentToSelectPurposeActivity = Intent(this, SelectPurposeActivity::class.java)
        val button:Button = findViewById(R.id.activity_select_target_part_button)
        val chestCheckBox: CheckBox = findViewById(R.id.activity_select_target_part_checkbox_chest)
        val armCheckBox: CheckBox = findViewById(R.id.activity_select_target_part_checkbox_arm)
        val shoulderCheckBox: CheckBox = findViewById(R.id.activity_select_target_part_checkbox_shoulder)
        val lowerbodyCheckBox: CheckBox = findViewById(R.id.activity_select_target_part_checkbox_lowerbody)
        val coreCheckBox: CheckBox = findViewById(R.id.activity_select_target_part_checkbox_core)
        val backCheckBox: CheckBox = findViewById(R.id.activity_select_target_part_checkbox_back)

        button.setOnClickListener {
            if (chestCheckBox.isChecked) stringJoiner.add("가슴")
            else if (armCheckBox.isChecked) stringJoiner.add("팔")
            else if (shoulderCheckBox.isChecked) stringJoiner.add("어깨")
            else if (lowerbodyCheckBox.isChecked) stringJoiner.add("하체")
            else if (coreCheckBox.isChecked) stringJoiner.add("복근")
            else if (backCheckBox.isChecked) stringJoiner.add("등")

            intentToSelectPurposeActivity.putExtra("part", stringJoiner.toString())
            startActivity(intentToSelectPurposeActivity)
        }
    }
}