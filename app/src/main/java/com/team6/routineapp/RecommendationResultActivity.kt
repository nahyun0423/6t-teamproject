package com.team6.routineapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.RelativeLayout.LayoutParams
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.team6.routineapp.fitness.Exercise
import com.team6.routineapp.fitness.Routine
import com.team6.routineapp.fitness.Training
import com.team6.routineapp.fitness.WeightTraining
import com.team6.routineapp.utility.convertFromDpToPx


class RecommendationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendation_result)

        val routine = getRecommendationFromAI()

        val intentToRoutineActivity = Intent(this, RoutineActivity::class.java)
        var intentToExerciseInformationActivity: Intent

        findViewById<Button>(R.id.activity_recommendation_result_button_not_needed).setOnClickListener {
            finish()
            startActivity(intentToRoutineActivity)
        }
        findViewById<Button>(R.id.activity_recommendation_result_button_add_to_my_routine).setOnClickListener {
            finish()
            intentToRoutineActivity.putExtra("routine", routine)
            startActivity(intentToRoutineActivity)
        }

        val trainingsLayout = findViewById<LinearLayout>(R.id.layout_trainings)
        var trainingLayout: ConstraintLayout
        var trainingIconImageView: ImageView
        var trainingNameTextView: TextView
        var trainingDetailTextView: TextView
        val blankSpaceLayout = RelativeLayout(this)

        var resource: Int

        for (training in routine.trainings) {
            resource = when (training!!.exercise.name) {
                "오버헤드 프레스" -> R.drawable.overhead_press
                "행잉 레그 레이즈" -> R.drawable.hanging_leg_raise
                else -> -1
            }

            trainingLayout =
                layoutInflater.inflate(R.layout.view_training, null) as ConstraintLayout
            trainingIconImageView = trainingLayout.findViewById(R.id.view_training_imageview)
            trainingNameTextView =
                trainingLayout.findViewById(R.id.view_training_textview_exercise_name)
            trainingDetailTextView = trainingLayout.findViewById(R.id.view_training_textview_detail)

            trainingLayout.setOnClickListener {
                intentToExerciseInformationActivity =
                    Intent(this, ExerciseInformationActivity::class.java)
                intentToExerciseInformationActivity.putExtra("exercise", training.exercise)
                startActivity(intentToExerciseInformationActivity)
            }

            trainingIconImageView.setImageResource(resource)
            trainingNameTextView.setText(training.exercise.name)
            trainingDetailTextView.setText(training.getDetail())

            trainingsLayout.addView(trainingLayout)
        }

        blankSpaceLayout.layoutParams = LayoutParams(convertFromDpToPx(80), convertFromDpToPx(210))
        trainingsLayout.addView(blankSpaceLayout)
    }

    private fun getRecommendationFromAI() : Routine {
        val overheadPress = Exercise("오버헤드 프레스", "팔", "바벨")
        val hangingLegRaise = Exercise("행잉 레그 레이즈", "코어", "행잉 레그 레이즈 머신")

        val training1 = WeightTraining(overheadPress, 4, 10, 40)
        val training2 = Training(hangingLegRaise, 4, 10)
        val training3 = WeightTraining(overheadPress, 4, 10, 30)
        val training4 = Training(hangingLegRaise, 6, 5)
        val training5 = WeightTraining(overheadPress, 3, 10, 30)
        val training6 = Training(hangingLegRaise, 4, 10)

        return Routine(
            "AI루틴", arrayOf(training1, training2, training3, training4, training5, training6)
        )
    }

    private fun generateRoutineView(routine: Routine): View {
        val layoutParameters = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParameters.setMargins(0, 0, 0, convertFromDpToPx(20))
        val intent = Intent(this, RoutineInformationActivity::class.java)
        val routineName = routine.name
        intent.putExtra("name", routineName)

        val routineView = layoutInflater.inflate(R.layout.view_routine, null)
        val routineViewtrainingsLayout =
            routineView.findViewById<LinearLayout>(R.id.view_routine_layout_trainings)
        var routineViewTrainingsTextView: TextView

        routineView.layoutParams = layoutParameters

        routineView.findViewById<TextView>(R.id.view_routine_textview_name).text = routineName
        routineView.findViewById<TextView>(R.id.view_routine_textview_part).text =
            routine.trainings[0]!!.exercise.part
        routineView.findViewById<ImageView>(R.id.view_routine_imageview)
            .setImageResource(R.drawable.benchpress_image)

        for (training in routine.trainings) {
            routineViewTrainingsTextView = TextView(this)
            routineViewTrainingsTextView.setText(training!!.exercise.name)
            routineViewtrainingsLayout.addView(routineViewTrainingsTextView)
        }

        routineView.setOnClickListener {
            startActivity(intent)
        }

        return routineView
    }
}