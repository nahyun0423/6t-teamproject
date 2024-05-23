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

class RegisterActivity : AppCompatActivity() {
    private lateinit var intentToRoutineActivity: Intent
    private lateinit var identifierEditText : EditText
    private lateinit var passwordEditText: EditText
    private lateinit var passwordCheckEditText: EditText
    private lateinit var button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        intentToRoutineActivity = Intent(this, RoutineActivity::class.java)

        button = findViewById(R.id.activity_register_button)
        identifierEditText = findViewById(R.id.activity_register_edittext_identifier)
        passwordEditText = findViewById(R.id.activity_register_edittext_password)
        passwordCheckEditText = findViewById(R.id.activity_register_edittext_password_check)

        button.setOnClickListener {
            val identifier = identifierEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (password.length < 8) {
                Toast.makeText(this, "설정한 비밀번호가 8자리 미만입니다. 조건에 맞게 다시 설정해주세요.", Toast.LENGTH_LONG).show()
            }

            else {
                val newUserDTO = UserDTO(identifier, password)
                RetrofitClient.userService.signUp(userDTO).enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.isSuccessful && response.body() == "success") {
                            userDTO = newUserDTO
                            startActivity(intentToRoutineActivity)
                        } else {
                            Toast.makeText(this@RegisterActivity, "이미 사용 중인 아이디입니다.", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            }
        }
    }
}