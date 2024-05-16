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
import com.team6.routineapp.utility.getCategory
import com.team6.routineapp.utility.getImageResource

class SelectExerciseActivity() : AppCompatActivity() {
    private var resource = 0
    private var category = ""
    private var training: Training? = null
    private var trainings: Array<Training?> = arrayOf()

    private lateinit var intentToExerciseInformationActivity: Intent
    private lateinit var intentToRoutineActivity: Intent

    private lateinit var inputTrainingInformationDialog: Dialog
    private lateinit var inputTrainingInformationDialogSetEditText: EditText
    private lateinit var inputTrainingInformationDialogNumberOfTimesEditText: EditText
    private lateinit var inputTrainingInformationDialogWeightEditText: EditText
    private lateinit var inputTrainingInformationDialogButton: Button

    private lateinit var inputRoutineNameDialog: Dialog
    private lateinit var inputRoutineNameDialogEditText: EditText
    private lateinit var inputRoutineNameDialogButton: Button

    private lateinit var exercisesLayout: LinearLayout

    private lateinit var bottomScrollView: HorizontalScrollView
    private lateinit var trainingsLayout: LinearLayout
    private lateinit var trainingIconViewImageView: ImageView
    private lateinit var trainingIconViewButton: Button
    private lateinit var view: View
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_exercise)

        /* Declare */
        val searchView: SearchView = findViewById(R.id.activity_select_exercise_searchview)

        /* Assign */
        intentToExerciseInformationActivity = Intent(this, ExerciseInformationActivity::class.java)
        intentToRoutineActivity = Intent(this, RoutineActivity::class.java)

        inputTrainingInformationDialog = Dialog(this)
        inputTrainingInformationDialog.setContentView(R.layout.dialog_input_training_information)
        inputTrainingInformationDialogButton =
            inputTrainingInformationDialog.findViewById(R.id.dialog_input_training_information_button)
        inputTrainingInformationDialogSetEditText =
            inputTrainingInformationDialog.findViewById(R.id.dialog_input_training_information_edittext_set)
        inputTrainingInformationDialogNumberOfTimesEditText =
            inputTrainingInformationDialog.findViewById(R.id.dialog_input_training_information_edittext_number_of_times)
        inputTrainingInformationDialogWeightEditText =
            inputTrainingInformationDialog.findViewById(R.id.dialog_input_training_information_edittext_weight)

        inputRoutineNameDialog = Dialog(this)
        inputRoutineNameDialog.setContentView(R.layout.dialog_input_routine_name)
        inputRoutineNameDialogEditText =
            inputRoutineNameDialog.findViewById(R.id.dialog_input_routine_name_edittext)
        inputRoutineNameDialogButton =
            inputRoutineNameDialog.findViewById(R.id.dialog_input_routine_name_button)

        exercisesLayout = findViewById(R.id.activity_select_exercise_layout_exercises)

        bottomScrollView = findViewById(R.id.activity_select_exercise_scrollview_bottom)
        trainingsLayout = findViewById(R.id.activity_select_exercise_layout_trainings)
        view = findViewById(R.id.activity_select_exercise_view)
        button = findViewById(R.id.activity_select_exercise_button)


        /* */
        inputRoutineNameDialogButton.setOnClickListener {
            val routineName = inputRoutineNameDialogEditText.text.toString()

            if (routineName == "") {
                Toast.makeText(this, "루틴 이름을 지어주세요.", Toast.LENGTH_SHORT).show()
            } else {
                intentToRoutineActivity.putExtra(
                    "routine", Routine(
                        inputRoutineNameDialogEditText.text.toString(), trainings
                    )
                )
                inputRoutineNameDialog.dismiss()
                startActivity(intentToRoutineActivity)
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                var exercise: Exercise

                if (query == null) return false

                for (exerciseView in exercisesLayout.children) exerciseView.visibility = View.GONE

                for (exerciseView in exercisesLayout.children) {
                    exercise = exerciseView.tag as Exercise

                    if (exercise.name.contains(query) || exercise.part.contains(query) || exercise.tool.contains(
                            query
                        )
                    ) exerciseView.visibility = View.VISIBLE
                }

                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }
        })

        generateExercisesView(exercises)

        button.setOnClickListener {
            if (trainings.isEmpty()) {
                Toast.makeText(this, "현재 루틴에 훈련이 없습니다. 훈련을 추가해 주세요.", Toast.LENGTH_SHORT).show()
            } else {
                inputRoutineNameDialog.show()
            }
        }
    }

    private fun generateTrainingIconView(training: Training) : ConstraintLayout {
        val trainingIconView =
            layoutInflater.inflate(R.layout.view_training_icon, null) as ConstraintLayout

        trainingIconViewImageView = trainingIconView.findViewById(R.id.view_training_icon_imageview)
        trainingIconViewButton = trainingIconView.findViewById(R.id.view_training_icon_button)

        trainingIconViewImageView.setImageResource(getImageResource(training.exercise))
        trainingIconViewButton.setOnClickListener {
            trainings.drop(trainings.indexOf(training))
            trainingsLayout.removeView(trainingIconView)
            bottomScrollView.invalidate()
            bottomScrollView.requestLayout()
        }

        return trainingIconView
    }

    private fun generateExerciseView(exercise: Exercise): ConstraintLayout {
        val exerciseView: ConstraintLayout =
            layoutInflater.inflate(R.layout.view_exercise, null) as ConstraintLayout
        val exerciseViewImageView: ImageView =
            exerciseView.findViewById(R.id.view_exercise_imageview)
        val exerciseViewNameTextView: TextView =
            exerciseView.findViewById(R.id.view_exercise_textview_name)
        val exerciseViewInformationTextView: TextView =
            exerciseView.findViewById(R.id.view_exercise_textview_information)
        val exerciseViewButton: Button = exerciseView.findViewById(R.id.view_exercise_button)

        resource = getImageResource(exercise)
        category = getCategory(exercise)

        exerciseView.setOnClickListener {
            intentToExerciseInformationActivity.putExtra("exercise", exercise)
            startActivity(intentToExerciseInformationActivity)
        }
        exerciseView.tag = exercise
        exerciseViewImageView.setImageResource(resource)
        exerciseViewNameTextView.text = exercise.name
        exerciseViewInformationTextView.text = String.format(
            "%s 자극을 위한 운동, 이용 기구: %s", exercise.part, exercise.tool
        )
        exerciseViewButton.setOnClickListener {
            inputTrainingInformationDialog.show()
            inputTrainingInformationDialogButton.setOnClickListener {
                training = when (category) {
                    "Training" -> Training(
                        exercise,
                        inputTrainingInformationDialogSetEditText.text.toString().toInt(),
                        inputTrainingInformationDialogNumberOfTimesEditText.text.toString().toInt()
                    )

                    "Weight Training" -> WeightTraining(
                        exercise,
                        inputTrainingInformationDialogSetEditText.text.toString().toInt(),
                        inputTrainingInformationDialogNumberOfTimesEditText.text.toString().toInt(),
                        inputTrainingInformationDialogWeightEditText.text.toString().toInt()
                    )

                    else -> null
                }

                trainings += training

                trainingsLayout.addView(generateTrainingIconView(training!!))
                trainingsLayout.removeView(view)
                trainingsLayout.addView(view)

                bottomScrollView.invalidate()
                bottomScrollView.requestLayout()

                inputTrainingInformationDialog.dismiss()
            }
        }

        return exerciseView
    }

    private fun generateExercisesView(exercises: Array<Exercise>) {
        for (exercise in exercises) exercisesLayout.addView(generateExerciseView(exercise))
    }
}