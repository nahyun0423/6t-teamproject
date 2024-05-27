package com.team6.routineapp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.team6.routineapp.dto.RoutineDTO
import com.team6.routineapp.fitness.Exercise
import com.team6.routineapp.fitness.Routine
import com.team6.routineapp.fitness.Training
import com.team6.routineapp.fitness.WeightTraining
import com.team6.routineapp.service.RetrofitClient
import com.team6.routineapp.utility.convertFromDpToPx
import com.team6.routineapp.utility.getImageResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendationResultActivity : AppCompatActivity() {
    var routineDTO = RoutineDTO()
    var routineName = "AI 추천 루틴"
    private lateinit var intentToRoutineActivity: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendation_result)
        activityStack.push(this)

        val inputRoutineNameDialog = generateInputRoutineNameDialog()
        val notNeededButton = findViewById<Button>(R.id.activity_recommendation_result_button_not_needed)
        val addToMyRoutineButton = findViewById<Button>(R.id.activity_recommendation_result_button_add_to_my_routine)

        intentToRoutineActivity = Intent(this, RoutineActivity::class.java)

        getRecommendationFromAI { routine ->
            if (routine != null) {


                notNeededButton.setOnClickListener {
                    activityStack.pop().finish()
                    activityStack.pop().finish()
                    activityStack.pop().finish()
                    activityStack.pop().finish()
                    activityStack.pop().finish()
                    startActivity(intentToRoutineActivity)
                }

                addToMyRoutineButton.setOnClickListener {
                    inputRoutineNameDialog.show()
                }

                generateTrainingsView(routine.trainings)
            } else {
                //예외 처리?
            }
        }
    }

    // UI를 위해 RoutineDTO를 Routine으로 변환
    private fun DTOtoRoutine(routine: RoutineDTO?): Array<Training?> {
        var trainingList = arrayOf<Training?>()
        if (routine != null) {
            for (exercises in routine.routineDetails!!) {
                val exercise = Exercise(exercises.exerciseName!!, "", "")
                val training = WeightTraining(exercise, exercises.sets!!, exercises.reps!!, exercises.weight!!)
                trainingList += training
            }
        }
        return trainingList
    }

    private fun getRecommendationFromAI(callback: (Routine?) -> Unit) {
        val query = String.format(
            "1. %s 2. %s 부위를 선호해 3. 1RM은 상체 벤치프레스 %dkg, 하체 스쿼트 %dkg이야 4. %s가 목적이야 5. %s에서 할 수 있었으면 좋겠어",
            userDTO.shape,
            intent.getStringExtra("part"),
            userDTO.rm_bench,
            userDTO.rm_squat,
            intent.getStringExtra("purpose"),
            intent.getStringExtra("place")
        )

        RetrofitClient.clovaService.getResponse(query).enqueue(object : Callback<RoutineDTO> {
            override fun onResponse(call: Call<RoutineDTO>, response: Response<RoutineDTO>) {
                if (response.isSuccessful) {
                    routineDTO = response.body()!!
                    val routine = Routine(routineName, DTOtoRoutine(routineDTO))
                    callback(routine)
                } else {
                    println("Error Code: ${response.code()}")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<RoutineDTO>, t: Throwable) {
                println("Error: ${t.message}")
                callback(null)
            }
        })
    }

    /* Training에 대응되는 View 만듦 */
    private fun generateTrainingView(training: Training): View {
        val intentToExerciseInformationActivity = Intent(this, ExerciseInformationActivity::class.java)

        val trainingLayout: ConstraintLayout = layoutInflater.inflate(R.layout.view_training, null) as ConstraintLayout
        val trainingIconImageView = trainingLayout.findViewById<ImageView>(R.id.view_exercise_imageview)
        val trainingNameTextView = trainingLayout.findViewById<TextView>(R.id.view_exercise_textview_name)
        val trainingDetailTextView = trainingLayout.findViewById<TextView>(R.id.view_training_textview_detail)

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
        blankSpaceLayout.layoutParams = RelativeLayout.LayoutParams(convertFromDpToPx(80), convertFromDpToPx(210))

        trainingsLayout.addView(blankSpaceLayout)
    }

    private fun generateInputRoutineNameDialog(): Dialog {
        val inputRoutineNameDialog = Dialog(this)
        inputRoutineNameDialog.setContentView(R.layout.dialog_input_routine_name)
        val inputRoutineNameDialogEditText: EditText =
            inputRoutineNameDialog.findViewById(R.id.dialog_input_routine_name_edittext)
        val inputRoutineNameDialogButton: Button =
            inputRoutineNameDialog.findViewById(R.id.dialog_input_routine_name_button)
        inputRoutineNameDialogButton.setOnClickListener {
            RetrofitClient.routineService.saveRoutine(
                RoutineDTO(
                    userDTO.userId, inputRoutineNameDialogEditText.text.toString(), routineDTO.routineDetails
                )
            ).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        activityStack.pop().finish()
                        activityStack.pop().finish()
                        activityStack.pop().finish()
                        activityStack.pop().finish()
                        activityStack.pop().finish()

                        startActivity(intentToRoutineActivity)
                    }

                    else Toast.makeText(this@RecommendationResultActivity, "루틴 생성에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@RecommendationResultActivity, "루틴 생성에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            })
        }

        return inputRoutineNameDialog
    }
}
