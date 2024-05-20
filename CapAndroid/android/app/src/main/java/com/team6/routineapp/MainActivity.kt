package com.team6.routineapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.team6.routineapp.dto.ExerciseDTO
import com.team6.routineapp.fitness.Exercise
import com.team6.routineapp.service.ExerciseService
import com.team6.routineapp.service.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

val overheadPress = Exercise("오버헤드 프레스", "팔", "바벨")
val hangingLegRaise = Exercise("행잉 레그 레이즈", "코어", "행잉 레그 레이즈 머신")
val exercises = arrayOf(overheadPress, hangingLegRaise)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var exerciseName = "레그 익스텐션"
        val exerciseServise = RetrofitClient.exerciseService
        val call = exerciseServise.getExercise(exerciseName)

        call.enqueue(object : retrofit2.Callback<ExerciseDTO> {
            override fun onResponse(call: Call<ExerciseDTO>, response: Response<ExerciseDTO>) {
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()
                    Log.d("ex", data!!.descriptions!!)
                }
            }

            override fun onFailure(call: Call<ExerciseDTO>, t: Throwable) {
            }
        })

    }
}
