package com.team6.routineapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import java.io.Serializable

open class Exercise(
    var name: String,
    var set: Int,
    var numberOfTimes: Int,
    var part: String,
    var tool: String
): Serializable {
    open fun getDetail(): String {
        return String.format("%d회, %d세트", numberOfTimes, set);
    }
}

class WeightTraining(
    name: String,
    set: Int,
    numberOfTimes: Int,
    part: String,
    tool: String,
    var weight: Int
) :
    Exercise(name, set, numberOfTimes, part, tool) {
    override fun getDetail(): String {
        return String.format("%dkg, ", weight) + super.getDetail()
    }
}

class Routine(var name: String, var exercises: Array<Exercise>) {
}

class RecommendationResultActivity : AppCompatActivity() {
    fun convertFromDpToPx(value: Int): Int {
        var displayMetrics = resources.displayMetrics
        var result = Math.round(value * displayMetrics.density)

        return result;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendation_result)

        val intentToRoutineActivity = Intent(this, RoutineActivity::class.java)
        var intentToExerciseInformationActivity: Intent

        findViewById<Button>(R.id.activityRecommendationResult_buttonNotNeeded).setOnClickListener {
            finish()
            startActivity(
                intentToRoutineActivity
            )
        }
        findViewById<Button>(R.id.activityRecommendationResult_buttonAddToMyRoutine).setOnClickListener {
            finish()
            startActivity(
                intentToRoutineActivity
            )
        }

        val exercise1 = WeightTraining("오버헤드 프레스", 4, 10, "팔", "바벨", 40)
        val exercise2 = Exercise("행잉 레그 레이즈", 4, 10, "코어", "행잉 레그 레이즈 머신")
        val exercise3 = Exercise("행잉 레그 레이즈", 4, 10, "코어", "행잉 레그 레이즈 머신")
        val exercise4 = Exercise("행잉 레그 레이즈", 4, 10, "코어", "행잉 레그 레이즈 머신")
        val exercise5 = Exercise("행잉 레그 레이즈", 4, 10, "코어", "행잉 레그 레이즈 머신")
        val exercise6 = Exercise("행잉 레그 레이즈", 4, 10, "코어", "행잉 레그 레이즈 머신")
        val routine = Routine(
            "AI루틴",
            arrayOf(exercise1, exercise2, exercise3, exercise4, exercise5, exercise6)
        )

        val middle = findViewById<LinearLayout>(R.id.activityRecommendationResult_layoutMiddle)
        var exerciseLayout: RelativeLayout
        var icon: ImageView
        var name: TextView
        var detail: TextView
        var parameter: RelativeLayout.LayoutParams
        var resource: Int

        for (exercise in routine.exercises) {
            when (exercise.name) {
                "오버헤드 프레스" -> resource = R.drawable.overhead_press
                "행잉 레그 레이즈" -> resource = R.drawable.hanging_leg_raise
                else -> resource = -1
            }

            exerciseLayout = RelativeLayout(this)
            icon = ImageView(this)
            name = TextView(this)
            detail = TextView(this)

            parameter = RelativeLayout.LayoutParams(convertFromDpToPx(300), convertFromDpToPx(80))
            parameter.setMargins(0, convertFromDpToPx(30), 0, 0)
            exerciseLayout.layoutParams = parameter
            exerciseLayout.setOnClickListener {
                intentToExerciseInformationActivity = Intent(this, ExerciseInformationActivity::class.java)
                intentToExerciseInformationActivity.putExtra("exercise", exercise)
                startActivity(intentToExerciseInformationActivity)
            }

            parameter = RelativeLayout.LayoutParams(convertFromDpToPx(80), convertFromDpToPx(80))
            icon.layoutParams = parameter
            icon.setImageResource(resource)

            parameter = RelativeLayout.LayoutParams(convertFromDpToPx(200), convertFromDpToPx(20))
            parameter.setMargins(convertFromDpToPx(100), convertFromDpToPx(10), 0, 0)
            name.layoutParams = parameter
            name.setTextAppearance(R.style.exercise_name)
            name.setText(exercise.name)

            parameter = RelativeLayout.LayoutParams(convertFromDpToPx(200), convertFromDpToPx(20))
            parameter.setMargins(convertFromDpToPx(100), convertFromDpToPx(40), 0, 0)
            detail.layoutParams = parameter
            detail.setTextAppearance(R.style.exercise_detail)
            detail.setText(exercise.getDetail())

            exerciseLayout.addView(icon)
            exerciseLayout.addView(name)
            exerciseLayout.addView(detail)
            middle.addView(exerciseLayout)
        }

        val blankSpace = RelativeLayout(this)
        parameter = RelativeLayout.LayoutParams(convertFromDpToPx(300), convertFromDpToPx(180))
        parameter.setMargins(0, 0, 0, convertFromDpToPx(30))
        blankSpace.layoutParams = parameter
        middle.addView(blankSpace)


    }
}