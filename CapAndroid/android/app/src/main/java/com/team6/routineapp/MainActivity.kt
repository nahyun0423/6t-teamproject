package com.team6.routineapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.team6.routineapp.dto.ExerciseDTO
import com.team6.routineapp.fitness.Exercise
import com.team6.routineapp.service.ExerciseService
import com.team6.routineapp.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val overheadPress = Exercise("오버헤드 프레스", "팔", "바벨")
val hangingLegRaise = Exercise("행잉 레그 레이즈", "코어", "행잉 레그 레이즈 머신")
var exerciseDTOs: List<ExerciseDTO> = listOf()
var exercises: Array<Exercise> = arrayOf()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Load Error", "LE")

        val exerciseSingleton = com.team6.routineapp.singletone.Exercise
        val exerciseService = RetrofitClient.exerciseService
        val call = exerciseService.getAllExercises()

        call.enqueue(object : Callback<List<ExerciseDTO>> {
            override fun onResponse(call: Call<List<ExerciseDTO>>, response: Response<List<ExerciseDTO>>) {
                if (response.isSuccessful && response.body() != null) {
                    exerciseDTOs = response.body()!!
                    exerciseDTOs.forEach { exerciseDTO ->
                        exerciseSingleton.addExercise(exerciseDTO)
                        exercises += Exercise(exerciseDTO.exerciseName!!, exerciseDTO.target!!, exerciseDTO.tools!!)
                    }
                }
            }

            override fun onFailure(call: Call<List<ExerciseDTO>>, t: Throwable) {
                Log.d("Load Error", t.toString())
            }
        })

        startActivity(Intent(this, LogInActivity::class.java))
    }
}
