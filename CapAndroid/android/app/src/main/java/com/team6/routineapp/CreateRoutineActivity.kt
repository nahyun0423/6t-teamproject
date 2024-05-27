package com.team6.routineapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.RectF
import android.graphics.Region
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import com.team6.routineapp.utility.convertFromDpToPx

class CreateRoutineActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_routine)
        activityStack.push(this)

        val intentToSelectTargetPartActivity = Intent(this, SelectTargetPartActivity::class.java)
        val intentToSelectExerciseActivity = Intent(this, SelectExerciseActivity::class.java)
        val intentToSettingActivity = Intent(this, SettingActivity::class.java)
        val buttonGetRecommendationFromAI = findViewById<Button>(R.id.activity_create_routine_button_get_recommendation_from_ai)
        val buttonMakeMyOwnRoutine = findViewById<Button>(R.id.activity_create_routine_button_make_my_own_routine)
        val buttonSetting :Button = findViewById(R.id.activity_create_routine_button_input_user_information)

        buttonGetRecommendationFromAI.setOnTouchListener { view, event ->
            val path = android.graphics.Path()
            var rectF = RectF()
            var region = Region()

            path.reset();
            path.moveTo(convertFromDpToPx(0).toFloat(), convertFromDpToPx(0).toFloat())
            path.lineTo(convertFromDpToPx(180).toFloat(),convertFromDpToPx(0).toFloat())
            path.lineTo(convertFromDpToPx(100).toFloat(), convertFromDpToPx(200).toFloat())
            path.lineTo(convertFromDpToPx(0).toFloat(), convertFromDpToPx(200).toFloat())
            path.close()
            path.computeBounds(rectF, true)
            region.setPath(path, Region(rectF.left.toInt(), rectF.top.toInt(), rectF.right.toInt(), rectF.bottom.toInt()))

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (region.contains(event.x.toInt(), event.y.toInt())) {
                        startActivity(intentToSelectTargetPartActivity)
                        true
                    }
                    false
                }
                else -> false
            }
            false
        }

        buttonMakeMyOwnRoutine.setOnTouchListener { view, event ->
            val path = android.graphics.Path()
            var rectF = RectF()
            var region = Region()

            path.reset();
            path.moveTo(convertFromDpToPx(100).toFloat(), convertFromDpToPx(0).toFloat())
            path.lineTo(convertFromDpToPx(180).toFloat(),convertFromDpToPx(0).toFloat())
            path.lineTo(convertFromDpToPx(180).toFloat(), convertFromDpToPx(200).toFloat())
            path.lineTo(convertFromDpToPx(0).toFloat(), convertFromDpToPx(200).toFloat())
            path.close()
            path.computeBounds(rectF, true)
            region.setPath(path, Region(rectF.left.toInt(), rectF.top.toInt(), rectF.right.toInt(), rectF.bottom.toInt()))

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (region.contains(event.x.toInt(), event.y.toInt())) {
                        startActivity(intentToSelectExerciseActivity)
                        true
                    }
                    false
                }
                else -> false
            }
            false
        }

        buttonSetting.setOnClickListener {
            startActivity(intentToSettingActivity)
        }
    }
}