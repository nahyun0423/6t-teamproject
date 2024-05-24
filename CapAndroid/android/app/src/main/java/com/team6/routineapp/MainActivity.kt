package com.team6.routineapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.team6.routineapp.dto.ExerciseDTO
import com.team6.routineapp.dto.UserDTO
import com.team6.routineapp.fitness.Exercise
import com.team6.routineapp.fitness.WeightTraining
import com.team6.routineapp.service.ExerciseService
import com.team6.routineapp.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import java.util.Stack

val activityStack = Stack<Activity>()
val overheadPress = Exercise("오버헤드 프레스", "팔", "바벨")
val hangingLegRaise = Exercise("행잉 레그 레이즈", "코어", "행잉 레그 레이즈 머신")
var userDTO: UserDTO = UserDTO()
var exerciseDTOs: List<ExerciseDTO> = listOf()
var exercises: Array<Exercise> = arrayOf()
val exerciseSingleton = com.team6.routineapp.singletone.Exercise

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitClient.exerciseService.getAllExercises().enqueue(object : Callback<List<ExerciseDTO>> {
            override fun onResponse(call: Call<List<ExerciseDTO>>, response: Response<List<ExerciseDTO>>) {
                if (response.isSuccessful && response.body() != null) {
                    exerciseDTOs = response.body()!!
                    exerciseDTOs.forEach { exerciseDTO ->
                        exerciseSingleton.addExercise(exerciseDTO)
                        exercises += Exercise(exerciseDTO.exerciseName!!, exerciseDTO.target!!, exerciseDTO.tools!!)
                    }

                    startActivity(Intent(this@MainActivity, LogInActivity::class.java))
                }
            }

            override fun onFailure(call: Call<List<ExerciseDTO>>, t: Throwable) {
                Log.d("Load Error", t.toString())
            }
        })

    }
}
