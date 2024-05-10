package com.team6.routineapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.TypedValue
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

open class Exercise(var name: String, var set: Int, var numberOfTimes: Int) {
    open fun getDetail(): String {
        return String.format("%d회, %d세트", numberOfTimes, set);
    }
}

class WeightTraining(name: String, set: Int, numberOfTimes: Int, var weight: Int): Exercise(name, set, numberOfTimes) {
    override fun getDetail(): String {
        return String.format("%dkg, ", weight) + super.getDetail()
    }
}

class RecommendationResultActivity : AppCompatActivity() {
    public fun convertFromDpToPx(value: Int): Int {
        var displayMetrics = resources.displayMetrics
        var result = Math.round(value * displayMetrics.density)

        return result;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendation_result)

        //이미지도 추가?
        val exercise1 = WeightTraining("오버헤드 프레스", 4, 10, 40)
        val exercise2 = Exercise("행잉 레그레이즈", 4, 10)
        val exercise3 = Exercise("행잉 레그레이즈", 4, 10)
        val exercise4 = Exercise("행잉 레그레이즈", 4, 10)
        val exercise5 = Exercise("행잉 레그레이즈", 4, 10)
        val exercise6 = Exercise("행잉 레그레이즈", 4, 10)
        val routine = arrayOf(exercise1, exercise2, exercise3, exercise4, exercise5, exercise6)

        val middle = findViewById<LinearLayout>(R.id.middle)
        var exercise: RelativeLayout
        var icon: ImageView
        var name: TextView
        var detail: TextView
        var parameter: RelativeLayout.LayoutParams
        var resource: String

        for (i in routine) {
            when(i.name) {
                "오버헤드 프레스" -> resource = "overhead_press"
                "행잉 레그레이즈" -> resource = "hanging_leg_raise"
                else -> resource = "none"
            }

            exercise = RelativeLayout(this)
            icon = ImageView(this)
            name = TextView(this)
            detail = TextView(this)

            parameter = RelativeLayout.LayoutParams(convertFromDpToPx(300), convertFromDpToPx(80))
            parameter.setMargins(0, convertFromDpToPx(30), 0, 0)
            exercise.layoutParams = parameter

            parameter = RelativeLayout.LayoutParams(convertFromDpToPx(80), convertFromDpToPx(80))
            icon.layoutParams = parameter
            //icon.setImageResource(R.drawable.resource)

            parameter = RelativeLayout.LayoutParams(convertFromDpToPx(200), convertFromDpToPx(20))
            parameter.setMargins(convertFromDpToPx(100), convertFromDpToPx(10), 0, 0)
            name.layoutParams = parameter
            name.setTextAppearance(R.style.exercise_name)
            name.setText(i.name)

            parameter = RelativeLayout.LayoutParams(convertFromDpToPx(200), convertFromDpToPx(20))
            parameter.setMargins(convertFromDpToPx(100), convertFromDpToPx(40), 0, 0)
            detail.layoutParams = parameter
            detail.setTextAppearance(R.style.exercise_detail)
            detail.setText(i.getDetail())

            exercise.addView(icon)
            exercise.addView(name)
            exercise.addView(detail)
            middle.addView(exercise)
        }

    }
}