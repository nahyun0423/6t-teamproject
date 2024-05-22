package com.team6.routineapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.RelativeLayout.LayoutParams
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.team6.routineapp.fitness.*
import com.team6.routineapp.utility.*


class RecommendationResultActivity : AppCompatActivity() {
    private var resource = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendation_result)

        /* 변수 선언 */
        val routine = getRecommendationFromAI()

        val intentToRoutineActivity = Intent(this, RoutineActivity::class.java)

        val notNeededButton =
            findViewById<Button>(R.id.activity_recommendation_result_button_not_needed)
        val addToMyRoutineButton =
            findViewById<Button>(R.id.activity_recommendation_result_button_add_to_my_routine)

        /* 처리 */
        notNeededButton.setOnClickListener {
            finish()
            startActivity(intentToRoutineActivity)
        } // 필요 없음 버튼을 누르면, Routine Activity로 이동
        addToMyRoutineButton.setOnClickListener {
            finish()
            intentToRoutineActivity.putExtra("routine", routine)
            startActivity(intentToRoutineActivity)
        } // 내 루틴에 추가하기 버튼을 누르면, 생성된 Routine과 함께 Routine Activity로 이동

        generateTrainingsView(routine.trainings)
    }

    /* AI 추천 Routine 불러 옴 */
    private fun getRecommendationFromAI(): Routine {
        val training1 = WeightTraining(overheadPress, 4, 10, 40)
        val training2 = Training(hangingLegRaise, 4, 10)
        val training3 = WeightTraining(overheadPress, 4, 10, 30)
        val training4 = Training(hangingLegRaise, 6, 5)
        val training5 = WeightTraining(overheadPress, 3, 10, 30)
        val training6 = Training(hangingLegRaise, 4, 10)

        return Routine(
            "AI 추천 루틴", arrayOf(training1, training2, training3, training4, training5, training6)
        )
    }

    /* Training에 대응되는 View 만듦 */
    private fun generateTrainingView(training: Training): View {
        val intentToExerciseInformationActivity =
            Intent(this, ExerciseInformationActivity::class.java)

        val trainingLayout: ConstraintLayout =
            layoutInflater.inflate(R.layout.view_training, null) as ConstraintLayout
        val trainingIconImageView =
            trainingLayout.findViewById<ImageView>(R.id.view_exercise_imageview)
        val trainingNameTextView =
            trainingLayout.findViewById<TextView>(R.id.view_exercise_textview_name)
        val trainingDetailTextView =
            trainingLayout.findViewById<TextView>(R.id.view_training_textview_detail)

        trainingLayout.setOnClickListener {
            intentToExerciseInformationActivity.putExtra("exercise", training.exercise)
            startActivity(intentToExerciseInformationActivity)
        }
        trainingIconImageView.setImageResource(getImageResource(training.exercise))
        trainingNameTextView.text = training.exercise.name
        trainingDetailTextView.text = training.getDetail()
        return trainingLayout
    }

    /* Training List에 대응되는 View 만듦 */
    private fun generateTrainingsView(trainings: Array<Training?>) {
        val trainingsLayout = findViewById<LinearLayout>(R.id.activity_recommendation_result_layout_trainings)
        val blankSpaceLayout = RelativeLayout(this)

        for (training in trainings) trainingsLayout.addView(generateTrainingView(training!!))
        blankSpaceLayout.layoutParams = LayoutParams(convertFromDpToPx(80), convertFromDpToPx(210))

        trainingsLayout.addView(blankSpaceLayout)
    }
}