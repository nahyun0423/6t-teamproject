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

class LogInActivity : AppCompatActivity() {
    private lateinit var intentToRegisterActivity: Intent
    private lateinit var intentToRoutineActivity: Intent

    private lateinit var inputIdEditText: EditText
    private lateinit var inputPasswordEditText: EditText

    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        intentToRegisterActivity = Intent(this, RegisterActivity::class.java)
        intentToRoutineActivity = Intent(this, RoutineActivity::class.java)

        inputIdEditText = findViewById(R.id.activity_log_in_edittext_identifier)
        inputPasswordEditText = findViewById(R.id.activity_log_in_edittext_password)

        loginButton = findViewById(R.id.activity_log_in_button_log_in)

        loginButton.setOnClickListener {
            val userId = inputIdEditText.text.toString().trim()
            val userPassword = inputPasswordEditText.text.toString().trim()

            RetrofitClient.userService.login(userId, userPassword).enqueue(object : Callback<UserDTO> {
                override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                    if (response.isSuccessful && response.body()?.userId != null) {
                        User.saveUser(response.body()!!)
                        userDTO = response.body()!!
                        startActivity(intentToRoutineActivity)
                    } else if (response.body()?.userId == null) {
                        //로그인 실패
                        Toast.makeText(this@LogInActivity, "Login Failed", Toast.LENGTH_SHORT).show()

                    } else {
                        // 오류 처리
                        println("Error Code: ${response.code()}")
                        Toast.makeText(this@LogInActivity, "error", Toast.LENGTH_SHORT).show()
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