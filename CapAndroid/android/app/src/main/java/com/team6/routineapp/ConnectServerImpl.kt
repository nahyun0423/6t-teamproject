package com.team6.routineapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.team6.routineapp.dto.ExerciseDTO
import com.team6.routineapp.dto.RoutineDTO
import com.team6.routineapp.dto.UserDTO
import com.team6.routineapp.service.RetrofitClient
import com.team6.routineapp.singletone.Exercise
import com.team6.routineapp.singletone.Routine
import com.team6.routineapp.singletone.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ConnectServerImpl : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_recommed_ex)

        val btn: Button = findViewById(R.id.btn1)
        val text: TextView = findViewById(R.id.textView1)
        val edit: EditText = findViewById(R.id.editText)

        //각 기능별로 필요한 서버 연결을 구현해놨습니다. 필요한 액티비티에 코드 가져가셔서
        // /*수정*/이라고 써있는데를 실제로 들어가야 할 값이나 필요한 비즈니스로직을 넣으시면 될거같습니다.

        //AI루틴생성
        val query = "1.키171 몸무게 68 근골격량 30 체지방량 55 2.하체운동제외 3. 다이어트 4. 상체운동 5. 헬스장 "/*수정*/
        RetrofitClient.clovaService.getResponse(query/*수정*/)
            .enqueue(object : Callback<RoutineDTO> {
                override fun onResponse(call: Call<RoutineDTO>, response: Response<RoutineDTO>) {
                    if (response.isSuccessful) {
                        /*수정*/
                        //routine에 임시 저장, 차후에 Routine.addRoutine(routine)으로 내 루틴에 추가
                        val routine: RoutineDTO? = response.body()
                    } else {
                        println("Error Code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<RoutineDTO>, t: Throwable) {
                    println("Error: ${t.message}")
                }
            })

        //루틴을 DB에 저장
        val routine = RoutineDTO() /*수정*/
        RetrofitClient.routineService.saveRoutine(routine/*수정*/).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    /*수정*/
                    println("루틴 저장 성공")
                } else {
                    println("루틴 저장 실패: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })

        //DB에 저장되어 있는 해당 유저의 모든 루틴 가져오기
        val userId = "dbtls" /*수정*/
        RetrofitClient.routineService.getAllRoutinesByUser(userId/*수정*/)
            .enqueue(object : Callback<List<RoutineDTO>> {
                override fun onResponse(call: Call<List<RoutineDTO>>,
                                        response: Response<List<RoutineDTO>>) {
                    if (response.isSuccessful) {
                        /*수정*/
                        val routines = response.body()
                        routines?.forEach { routine ->
                            Routine.addRoutine(routine) // 유저의 모든 루틴을 Routine에 추가
                            text.text = Routine.getAllRoutines().toString()
                        }
                    } else {
                        println("Error Code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<RoutineDTO>>, t: Throwable) {
                    println("Error: ${t.message}")
                }
            })



        //로그인기능
        val id = "dbtls" /*수정*/
        val password = "qwer" /*수정*/
        RetrofitClient.userService.login(id, password /*실제 입력된 아이디 비밀번호 넣기*/)
            .enqueue(object : Callback<UserDTO> {
                override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                    if (response.isSuccessful && response.body()?.userId != null) {

                        // 로그인 성공시, User 객체에 응답값 저장
                        User.saveUser(response.body()!!)
                    } else if (response.body()?.userId == null) {
                        /*수정*/
                        //로그인 실패
                    } else {
                        // 오류 처리
                        println("Error Code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                    // 네트워크 오류 또는 요청 실패 처리
                    println("Error: ${t.message}")
                }
            })


        //모든 운동 조회후 Exercise에 저장
        RetrofitClient.exerciseService.getAllExercises()
            .enqueue(object : Callback<List<ExerciseDTO>> {
                override fun onResponse(call: Call<List<ExerciseDTO>>, response: Response<List<ExerciseDTO>>) {
                    if (response.isSuccessful) {
                        response.body()?.forEach { exerciseDTO ->
                            Exercise.addExercise(exerciseDTO)
                        }
                    } else {
                        println("Error Code: ${response.code()}")

                    }
                }

                override fun onFailure(call: Call<List<ExerciseDTO>>, t: Throwable) {
                    println("Error: ${t.message}")
                }
            })

        //회원가입
        val userDTO = UserDTO(/*여기에 실제로 값 받아서 넣기*/"dbtls", "qwer", 172.3f, 77.2f, 30f, 60f, "남",null,40,35)
        RetrofitClient.userService.signUp(userDTO).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful && response.body() == "success") {
                    /*수정*/
                    //response.body()  =="success" 면 회원가입 성공(중복된 아이디 없음)
                    println("회원가입 성공")
                } else {
                    //response.body()  =="failure"면 실패
                    println("회원가입 실패")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })

        //유저 정보 수정
        RetrofitClient.userService.editUser(userDTO).enqueue(object : Callback<UserDTO> {
            override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                if (response.isSuccessful) {
                    User.saveUser(response.body()!!)
                } else {
                    println("Error Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })

        btn.setOnClickListener(){

        }


    }

}