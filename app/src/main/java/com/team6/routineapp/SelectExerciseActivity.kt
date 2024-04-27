package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SelectExerciseActivity : AppCompatActivity() {
    fun convertFromDpToPx(value: Int): Int {
        var displayMetrics = resources.displayMetrics
        return Math.round(value * displayMetrics.density)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_exercise)

        val intentToDetail = Intent(this, DetailInfoActivity::class.java)

        val exercise1 = WeightTraining("오버헤드 프레스", 4, 10, "팔", "바벨", 40)
        val exercise2 = Exercise("행잉 레그 레이즈", 4, 10, "코어", "행잉 레그 레이즈 머신")
        val exercise3 = Exercise("행잉 레그 레이즈", 4, 10, "코어", "행잉 레그 레이즈 머신")
        val exercise4 = Exercise("행잉 레그 레이즈", 4, 10, "코어", "행잉 레그 레이즈 머신")
        val exercise5 = Exercise("행잉 레그 레이즈", 4, 10, "코어", "행잉 레그 레이즈 머신")
        val exercise6 = Exercise("행잉 레그 레이즈", 4, 10, "코어", "행잉 레그 레이즈 머신")
        val routine = arrayOf(exercise1, exercise2, exercise3, exercise4, exercise5, exercise6)

        val middle = findViewById<LinearLayout>(R.id.selectExerciseActivityMiddleContainer)
        var exercise: RelativeLayout
        var icon: ImageView
        var name: TextView
        var detail: TextView
        var parameter: RelativeLayout.LayoutParams
        var resource: Int

        for (i in routine) {
            when (i.name) {
                "오버헤드 프레스" -> resource = R.drawable.overhead_press
                "행잉 레그 레이즈" -> resource = R.drawable.hanging_leg_raise
                else -> resource = -1
            }

            exercise = RelativeLayout(this)
            icon = ImageView(this)
            name = TextView(this)
            detail = TextView(this)

            parameter = RelativeLayout.LayoutParams(convertFromDpToPx(300), convertFromDpToPx(80))
            parameter.setMargins(30, convertFromDpToPx(30), 0, 0)
            exercise.layoutParams = parameter
            exercise.setOnClickListener{

            }

            parameter = RelativeLayout.LayoutParams(convertFromDpToPx(80), convertFromDpToPx(80))
            icon.layoutParams = parameter
            icon.setImageResource(resource)

            parameter = RelativeLayout.LayoutParams(convertFromDpToPx(200), convertFromDpToPx(20))
            parameter.setMargins(convertFromDpToPx(100), convertFromDpToPx(10), 0, 0)
            name.layoutParams = parameter
            name.setTextAppearance(R.style.exercise_name)
            name.setText(i.name)

            exercise.addView(icon)
            exercise.addView(name)
            exercise.addView(detail)
            middle.addView(exercise)
        }

        var selectedExercise = ImageView(this)


        selectedExercise.setBackgroundResource(R.drawable.circle)
        selectedExercise.setImageResource(R.drawable.overhead_press)
        parameter = RelativeLayout.LayoutParams(convertFromDpToPx(80), convertFromDpToPx(80))
        selectedExercise.layoutParams = parameter

        var bottom = findViewById<LinearLayout>(R.id.selectExerciseActivityBottomContainer)
        bottom.addView(selectedExercise)

    }
}