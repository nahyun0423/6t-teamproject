package com.team6.routineapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.RelativeLayout

class RecommendationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendation_result)

        for (i : Int in 1..6) {
            val routine = RelativeLayout(this, )
        }
    }
}