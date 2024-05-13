package com.team6.routineapp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.team6.routineapp.fitness.Exercise
import com.team6.routineapp.fitness.Routine
import com.team6.routineapp.fitness.Training
import com.team6.routineapp.fitness.WeightTraining

class SelectExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_exercise)

        val overheadPress = Exercise("오버헤드 프레스", "팔", "바벨")
        val hangingLegRaise = Exercise("행잉 레그 레이즈", "코어", "행잉 레그 레이즈 머신")
        val exercises = arrayOf(overheadPress, hangingLegRaise)
        var training: Training?
        var trainings = arrayOf<Training?>()
        var resource: Int
        var category: String

        val intentToExerciseInformationActivity =
            Intent(this, ExerciseInformationActivity::class.java)
        val intentToRoutineActivity = Intent(this, RoutineActivity::class.java)

        val inputTrainingInformationDialog = Dialog(this)
        inputTrainingInformationDialog.setContentView(R.layout.dialog_input_training_information)
        val inputTrainingInformationDialogNumberOfTimesEditText =
            inputTrainingInformationDialog.findViewById<EditText>(R.id.dialog_input_training_information_edittext_number_of_times)
        val inputTrainingInformationDialogSetEditText =
            inputTrainingInformationDialog.findViewById<EditText>(R.id.dialog_input_training_information_edittext_set)
        val inputTrainingInformationDialogWeightEditText =
            inputTrainingInformationDialog.findViewById<EditText>(R.id.dialog_input_training_information_edittext_weight)
        val inputTrainingInformationDialogButton =
            inputTrainingInformationDialog.findViewById<Button>(R.id.dialog_input_training_information_button)

        val inputRoutineNameDialog = Dialog(this)
        inputRoutineNameDialog.setContentView(R.layout.dialog_input_routine_name)
        val inputRoutineNameDialogTextView =
            inputRoutineNameDialog.findViewById<TextView>(R.id.dialog_input_routine_name_textview)
        val inputRoutineNameDialogEditText =
            inputRoutineNameDialog.findViewById<EditText>(R.id.dialog_input_routine_name_edittext)
        val inputRoutineNameDialogButton =
            inputRoutineNameDialog.findViewById<Button>(R.id.dialog_input_routine_name_button)
        inputRoutineNameDialogButton.setOnClickListener {
            val routineName = inputRoutineNameDialogEditText.text.toString()

            if (routineName == "") {
                Toast.makeText(this, "루틴 이름을 지어주세요.", Toast.LENGTH_SHORT).show()
            } else {
                intentToRoutineActivity.putExtra(
                    "routine", Routine(inputRoutineNameDialogEditText.text.toString(), trainings)
                )
                inputRoutineNameDialog.dismiss()
                startActivity(intentToRoutineActivity)
            }
        }

        val searchView = findViewById<SearchView>(R.id.activity_select_exercise_searchview)

        val exercisesLayout =
            findViewById<LinearLayout>(R.id.activitySelectExercise_layoutExercises)
        var exerciseView: ConstraintLayout
        var exerciseViewImageView: ImageView
        var exerciseViewNameTextView: TextView
        var exerciseViewInformationTextView: TextView
        var exerciseViewButton: Button

        val bottomScrollView =
            findViewById<HorizontalScrollView>(R.id.activity_select_exercise_scrollview_bottom)
        val trainingsLayout =
            findViewById<LinearLayout>(R.id.activity_select_exercise_layout_trainings)
        var trainingIconView: ConstraintLayout
        var trainingIconViewImageView: ImageView
        var trainingIconViewButton: Button
        val finishButton = findViewById<Button>(R.id.activity_select_exercise_button)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val result = ArrayList<View>()

                if (query == null) {
                    return false
                }

                exercisesLayout.findViewsWithText(result, query, View.FIND_VIEWS_WITH_TEXT)

                for (view in exercisesLayout.children) {
                    view.visibility = View.INVISIBLE
                }

                for(view in result) {
                    (view.parent as View).visibility = View.VISIBLE
                }

                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {

                return true
            }
        })

        for (exercise in exercises) {
            resource = when (exercise.name) {
                "오버헤드 프레스" -> R.drawable.overhead_press
                "행잉 레그 레이즈" -> R.drawable.hanging_leg_raise
                else -> 0
            }

            category = when (exercise.tool) {
                "바벨" -> "Weight Training"
                else -> "Training"
            }

            exerciseView = layoutInflater.inflate(R.layout.view_exercise, null) as ConstraintLayout
            exerciseViewImageView = exerciseView.findViewById(R.id.view_training_imageview)
            exerciseViewNameTextView =
                exerciseView.findViewById(R.id.view_training_textview_exercise_name)
            exerciseViewInformationTextView =
                exerciseView.findViewById(R.id.view_exercises_textview_information)
            exerciseViewButton = exerciseView.findViewById(R.id.view_exercise_button)

            exerciseView.setOnClickListener {
                intentToExerciseInformationActivity.putExtra("exercise", exercise)
                startActivity(intentToExerciseInformationActivity)
            }
            exerciseViewImageView.setImageResource(resource)
            exerciseViewNameTextView.setText(exercise.name)
            exerciseViewInformationTextView.setText(
                String.format(
                    "%s 자극을 위한 운동, 이용 기구: %s", exercise.part, exercise.tool
                )
            )
            exerciseViewButton.setOnClickListener {
                inputTrainingInformationDialog.show()
                inputTrainingInformationDialogButton.setOnClickListener {
                    training = when (category) {
                        "Training" -> Training(
                            exercise,
                            inputTrainingInformationDialogSetEditText.text.toString().toInt(),
                            inputTrainingInformationDialogNumberOfTimesEditText.text.toString()
                                .toInt()
                        )

                        "Weight Training" -> WeightTraining(
                            exercise,
                            inputTrainingInformationDialogSetEditText.text.toString().toInt(),
                            inputTrainingInformationDialogNumberOfTimesEditText.text.toString()
                                .toInt(),
                            inputTrainingInformationDialogWeightEditText.text.toString().toInt()
                        )

                        else -> null
                    }
                    trainings += training

                    trainingIconView = layoutInflater.inflate(
                        R.layout.view_training_icon, null
                    ) as ConstraintLayout
                    trainingIconViewImageView =
                        trainingIconView.findViewById(R.id.view_training_icon_imageview)
                    trainingIconViewButton =
                        trainingIconView.findViewById(R.id.view_training_icon_button)
                    trainingIconViewImageView.setImageResource(resource)
                    trainingIconViewButton.setOnClickListener { view ->
                        trainings.drop(trainings.indexOf(training))
                        trainingsLayout.removeView(view.parent as View)

                        bottomScrollView.invalidate()
                        bottomScrollView.requestLayout()
                    }

                    trainingsLayout.addView(trainingIconView)

                    bottomScrollView.invalidate()
                    bottomScrollView.requestLayout()

                    inputTrainingInformationDialog.dismiss()
                }
            }

            exercisesLayout.addView(exerciseView)
        }

        finishButton.setOnClickListener {
            if (trainings.isEmpty()) {
                Toast.makeText(this, "현재 루틴에 훈련이 없습니다. 훈련을 추가해 주세요.", Toast.LENGTH_SHORT).show()
            } else {
                inputRoutineNameDialog.show()
            }
        }
    }
}