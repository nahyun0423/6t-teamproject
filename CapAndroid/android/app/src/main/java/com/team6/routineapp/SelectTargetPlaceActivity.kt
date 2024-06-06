package com.team6.routineapp

import android.content.Intent
import android.graphics.RectF
import android.graphics.Region
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.team6.routineapp.utility.convertFromDpToPx

class SelectTargetPlaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_target_place)
        activityStack.push(this)

        val intentToRecommendationResultActivity = Intent(this, RecommendationResultActivity::class.java)
        val gymButton: Button = findViewById(R.id.activity_select_target_place_gym)
        val homeButton: Button = findViewById(R.id.activity_select_target_place_button_home)

        gymButton.setOnTouchListener { view, event ->
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
                        intentToRecommendationResultActivity.putExtra("part", intent.getStringExtra("part"))
                        intentToRecommendationResultActivity.putExtra("purpose", intent.getStringExtra("purpose"))
                        intentToRecommendationResultActivity.putExtra("place", "헬스장")
                        startActivity(intentToRecommendationResultActivity)
                        true
                    }
                    false
                }
                else -> false
            }
            false
        }

        homeButton.setOnTouchListener { view, event ->
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
                        intentToRecommendationResultActivity.putExtra("part", intent.getStringExtra("part"))
                        intentToRecommendationResultActivity.putExtra("purpose", intent.getStringExtra("purpose"))
                        intentToRecommendationResultActivity.putExtra("place", "집")
                        startActivity(intentToRecommendationResultActivity)
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