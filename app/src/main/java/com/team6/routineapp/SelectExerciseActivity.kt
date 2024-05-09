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
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.team6.routineapp.fitness.Exercise
import com.team6.routineapp.fitness.Routine
import com.team6.routineapp.fitness.Training
import com.team6.routineapp.fitness.WeightTraining

class SelectExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_exercise)

        val intentToExerciseInformationActivity =
            Intent(this, ExerciseInformationActivity::class.java)
        val intentToRoutineActivity = Intent(this, RoutineActivity::class.java)

        val overheadPress = Exercise("오버헤드 프레스", "팔", "바벨")
        val hangingLegRaise = Exercise("행잉 레그 레이즈", "코어", "행잉 레그 레이즈 머신")
        val exercises = arrayOf(overheadPress, hangingLegRaise)
        var training: Training
        val trainings = arrayOf<Training>()

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

        val exercisesLayout =
            findViewById<LinearLayout>(R.id.activitySelectExercise_layoutExercises)
        var exerciseViewLayout: ConstraintLayout
        var exerciseViewIconImageView: ImageView
        var exerciseViewNameTextView: TextView
        var exerciseViewInformationTextView: TextView
        var exerciseViewAddButton: Button

        val bottomScrollView =
            findViewById<HorizontalScrollView>(R.id.activity_select_exercise_scrollview_bottom)
        val trainingsLayout =
            findViewById<LinearLayout>(R.id.activity_select_exercise_layout_trainings)
        var trainingIconViewLayout: ConstraintLayout
        var trainingIconViewImageView: ImageView
        var trainingIconViewDeleteButton: Button
        val finishButton = findViewById<Button>(R.id.activity_select_exercise_button)

        var resource: Int
        var category: String

        for (exercise in exercises) {
            resource = when (exercise.name) {
                "오버헤드 프레스" -> R.drawable.overhead_press
                "행잉 레그 레이즈" -> R.drawable.hanging_leg_raise
                else -> -1
            }

            category = when (exercise.tool) {
                "바벨" -> "Weight Training"
                else -> "Training"
            }

            exerciseViewLayout =
                layoutInflater.inflate(R.layout.view_exercise, null) as ConstraintLayout
            exerciseViewIconImageView =
                exerciseViewLayout.findViewById(R.id.view_training_imageview)
            exerciseViewNameTextView =
                exerciseViewLayout.findViewById(R.id.view_training_textview_exercise_name)
            exerciseViewInformationTextView =
                exerciseViewLayout.findViewById(R.id.view_exercises_textview_information)
            exerciseViewAddButton = exerciseViewLayout.findViewById(R.id.view_exercise_button)

            exerciseViewLayout.setOnClickListener {
                intentToExerciseInformationActivity.putExtra("Exercise", exercise)
                startActivity(intentToExerciseInformationActivity)
            }

            exerciseViewIconImageView.setImageResource(resource)
            exerciseViewNameTextView.setText(exercise.name)
            exerciseViewInformationTextView.setText(
                String.format(
                    "자극을 위한 운동, 이용 기구: %s", exercise.tool
                )
            )
            exerciseViewAddButton.setOnClickListener {
                inputTrainingInformationDialog.show()
                inputTrainingInformationDialogButton.setOnClickListener {
                    training = when (category) {
                        "Weight Training" -> WeightTraining(
                            exercise,
                            inputTrainingInformationDialogSetEditText.text.toString().toInt(),
                            inputTrainingInformationDialogNumberOfTimesEditText.text.toString()
                                .toInt(),
                            inputTrainingInformationDialogWeightEditText.text.toString().toInt()
                        )

                        "Training" -> Training(
                            exercise,
                            inputTrainingInformationDialogSetEditText.text.toString().toInt(),
                            inputTrainingInformationDialogNumberOfTimesEditText.text.toString()
                                .toInt()
                        )

                        else -> Training(exercise, -1, -1)
                    }

                    trainings.plus(training)

                    trainingIconViewLayout = layoutInflater.inflate(
                        R.layout.view_training_icon, null
                    ) as ConstraintLayout
                    trainingIconViewImageView =
                        trainingIconViewLayout.findViewById(R.id.view_training_icon_imageview)
                    trainingIconViewDeleteButton =
                        trainingIconViewLayout.findViewById(R.id.view_training_icon_button)
                    trainingIconViewImageView.setImageResource(resource)
                    trainingIconViewDeleteButton.setOnClickListener { view ->
                        trainings.drop(trainings.indexOf(training))
                        trainingsLayout.removeView(view.parent as View)

                        bottomScrollView.invalidate()
                        bottomScrollView.requestLayout()
                    }

                    trainingsLayout.addView(trainingIconViewLayout)

                    bottomScrollView.invalidate()
                    bottomScrollView.requestLayout()

                    inputTrainingInformationDialog.dismiss()
                }
            }

            exercisesLayout.addView(exerciseViewLayout)
        }

        finishButton.setOnClickListener {
            intentToRoutineActivity.putExtra("Routine", Routine("My Routine", trainings))
            startActivity(intentToRoutineActivity)
        }
    }
}