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
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.team6.routineapp.fitness.Exercise
import com.team6.routineapp.fitness.Training
import com.team6.routineapp.fitness.WeightTraining

class SelectExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_exercise)

        val intentToExerciseInformationActivity =
            Intent(this, ExerciseInformationActivity::class.java)

        val overheadPress = Exercise("오버헤드 프레스", "팔", "바벨")
        val hangingLegRaise = Exercise("행잉 레그 레이즈", "코어", "행잉 레그 레이즈 머신")
        val exercises = arrayOf(overheadPress, hangingLegRaise)
        var training: Training?
        val routine = arrayListOf<Training?>()

        val inputTrainingInformationDialog = Dialog(this)
        inputTrainingInformationDialog.setContentView(R.layout.dialog_input_training_information)
        val inputTrainingInformationDialogNumberOfTimesEditText =
            inputTrainingInformationDialog.findViewById<EditText>(R.id.dialogInputTrainingInformation_editTextNumberOfTimes)
        val inputTrainingInformationDialogSetEditText =
            inputTrainingInformationDialog.findViewById<EditText>(R.id.dialogInputTrainingInformation_editTextSet)
        val inputTrainingInformationDialogWeightEditText =
            inputTrainingInformationDialog.findViewById<EditText>(R.id.dialogInputTrainingInformation_editTextWeight)
        val inputTrainingInformationDialogButton =
            inputTrainingInformationDialog.findViewById<Button>(R.id.dialogInputTrainingInformation_button)

        val exercisesLayout =
            findViewById<LinearLayout>(R.id.activitySelectExercise_layoutExercises)
        var exerciseViewLayout: ConstraintLayout
        var exerciseViewIconImageView: ImageView
        var exerciseViewNameTextView: TextView
        var exerciseViewInformationTextView: TextView
        var exerciseViewAddButton: Button

        val routineLayout = findViewById<LinearLayout>(R.id.activitySelectExercise_layoutRoutine)
        val routineScrollView =
            findViewById<HorizontalScrollView>(R.id.activitySelectExercise_scrollViewBottom)
        var trainingIconViewLayout: ConstraintLayout
        var trainingIconViewImageView: ImageView
        var trainingIconViewDeleteButton: Button

        var resource: Int
        var category: String

        for (exercise in exercises) {
            resource = when (exercise.name) {
                "오버헤드 프레스" -> R.drawable.overhead_press
                "행잉 레그 레이즈" -> R.drawable.hanging_leg_raise
                else -> -1
            }

            category = when (exercise.name) {
                "오버헤드 프레스" -> "WeightTraining"
                "행잉 레그 레이즈" -> "Training"
                else -> ""
            }

            exerciseViewLayout =
                layoutInflater.inflate(R.layout.view_exercise, null) as ConstraintLayout
            exerciseViewIconImageView =
                exerciseViewLayout.findViewById(R.id.viewExercise_imageViewIcon)
            exerciseViewNameTextView =
                exerciseViewLayout.findViewById(R.id.viewExercise_textViewName)
            exerciseViewInformationTextView =
                exerciseViewLayout.findViewById(R.id.viewExercise_textViewInformation)
            exerciseViewAddButton = exerciseViewLayout.findViewById(R.id.viewExercise_buttonAdd)

            exerciseViewLayout.setOnClickListener {
                intentToExerciseInformationActivity.putExtra("exercise", exercise)
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
                            inputTrainingInformationDialogNumberOfTimesEditText.text.toString().toInt(),
                            inputTrainingInformationDialogWeightEditText.text.toString().toInt()
                        )

                        "Training" -> Training(exercise, inputTrainingInformationDialogSetEditText.text.toString().toInt(), inputTrainingInformationDialogNumberOfTimesEditText.text.toString().toInt())
                        else -> null
                    }

                    routine.add(training)

                    trainingIconViewLayout =
                        layoutInflater.inflate(R.layout.view_training_icon, null) as ConstraintLayout
                    trainingIconViewImageView =
                        trainingIconViewLayout.findViewById(R.id.viewTrainingIcon_imageView)
                    trainingIconViewDeleteButton =
                        trainingIconViewLayout.findViewById(R.id.activitySelectExercise_buttonDelete)
                    trainingIconViewImageView.setImageResource(resource)
                    trainingIconViewDeleteButton.setOnClickListener { view ->
                        routine.remove(training)
                        routineLayout.removeView(view.parent as View)

                        routineScrollView.invalidate()
                        routineScrollView.requestLayout()
                    }

                    routineLayout.addView(trainingIconViewLayout)

                    routineScrollView.invalidate()
                    routineScrollView.requestLayout()
                    inputTrainingInformationDialog.dismiss()
                }
            }

            exercisesLayout.addView(exerciseViewLayout)
        }
    }
}