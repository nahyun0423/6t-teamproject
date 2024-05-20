package com.team6.routineapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.team6.routineapp.dto.UserDTO
import com.team6.routineapp.service.RetrofitClient
import com.team6.routineapp.singletone.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var intentToRegisterActivity: Intent
    private lateinit var intentToCreateRoutineActivity: Intent

    private lateinit var inputIdEditText: EditText
    private lateinit var inputPasswordEditText: EditText

    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        intentToRegisterActivity = Intent(this, RegisterActivity::class.java)
        intentToCreateRoutineActivity = Intent(this, CreateRoutineActivity::class.java)

        inputIdEditText = findViewById(R.id.login_id)
        inputPasswordEditText = findViewById(R.id.login_pass)

        loginButton = findViewById(R.id.button_login)

        loginButton.setOnClickListener {
            val userId = inputIdEditText.text.toString().trim()
            val userPassword = inputPasswordEditText.text.toString().trim()

            RetrofitClient.userService.login(userId, userPassword)
                .enqueue(object : Callback<UserDTO> {
                    override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                        if (response.isSuccessful && response.body()?.userId != null) {
                            // 로그인 성공시, User 객체에 응답값 저장
                            User.loginUser(response.body()!!)
                            //intentToCreateRoutineActivity.putExtra()
                            startActivity(intentToCreateRoutineActivity)
                        } else if (response.body()?.userId == null) {
                            //로그인 실패
                            Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_SHORT).show()

                        } else {
                            // 오류 처리
                            println("Error Code: ${response.code()}")
                            Toast.makeText(this@LoginActivity, "error", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                        // 네트워크 오류 또는 요청 실패 처리
                        println("Error: ${t.message}")
                    }
                })
        }
    }
}