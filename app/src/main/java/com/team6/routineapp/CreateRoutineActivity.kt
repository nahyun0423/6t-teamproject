package com.team6.routineapp

import android.content.Intent
import android.graphics.RectF
import android.graphics.Region
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import com.team6.routineapp.utility.convertFromDpToPx

class CreateRoutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_routine)

        val intentToPreferexerciseActivity = Intent(this, PreferexerciseActivity::class.java)
        val intentToSelectExerciseActivity = Intent(this, SelectExerciseActivity::class.java)
        val buttonGetRecommendationFromAI = findViewById<Button>(R.id.activityCreateRoutine_buttonGetRecommendationFromAI)
        val buttonMakeMyOwnRoutine = findViewById<Button>(R.id.activityCreateRoutine_buttonMakeMyOwnRoutine)

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
                    if (region.contains(event.getX().toInt(), event.getY().toInt())) {
                        startActivity(intentToPreferexerciseActivity)
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
                    if (region.contains(event.getX().toInt(), event.getY().toInt())) {
                        startActivity(intentToSelectExerciseActivity)
                        true
                    }
                    false
                }
                else -> false
            }
            false
        }
    }
}