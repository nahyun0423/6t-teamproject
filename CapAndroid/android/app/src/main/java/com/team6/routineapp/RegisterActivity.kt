package com.team6.routineapp

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.team6.routineapp.dto.RoutineDTO
import com.team6.routineapp.dto.UserDTO
import com.team6.routineapp.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var newUserDTO: UserDTO

    private lateinit var intentToRoutineActivity: Intent
    private lateinit var inputPhysicalInformationDialog: Dialog
    private lateinit var identifierEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var passwordCheckEditText: EditText
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        activityStack.push(this)

        intentToRoutineActivity = Intent(this, RoutineActivity::class.java)
        inputPhysicalInformationDialog = generateDialog()
        button = findViewById(R.id.activity_register_button)
        identifierEditText = findViewById(R.id.activity_register_edittext_identifier)
        passwordEditText = findViewById(R.id.activity_register_edittext_password)
        passwordCheckEditText = findViewById(R.id.activity_register_edittext_password_check)

        button.setOnClickListener {
            val identifier = identifierEditText.text.toString()
            val password = passwordEditText.text.toString()
            val passwordCheck = passwordCheckEditText.text.toString()

            if (password.length < 8) {
                Toast.makeText(this, "설정한 비밀번호가 8자리 미만입니다. 조건에 맞게 다시 설정해주세요.", Toast.LENGTH_LONG).show()
            } else if (password != passwordCheck) {
                Toast.makeText(this, "비밀번호 확인 문자열이 틀립니다. 다시 확인해 주세요.", Toast.LENGTH_LONG).show()
            } else {
                newUserDTO = UserDTO(identifier, password)
                RetrofitClient.userService.checkDup(newUserDTO).enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.isSuccessful && response.body() == "success") {
                            inputPhysicalInformationDialog.show()
                        } else {
                            Toast.makeText(this@RegisterActivity, "이미 사용 중인 아이디입니다.", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.d("response", "fail")
                    }
                })
            }
        }
    }

    private fun generateDialog(): Dialog {
        inputPhysicalInformationDialog = Dialog(this)
        inputPhysicalInformationDialog.setContentView(R.layout.dialog_input_physical_information)

        var height: Float
        var weight: Float
        var muscleMass: Float
        var fatMass: Float
        var gender = ""
        var upperbodyRM: Int
        var lowerbodyRM: Int


        val heightEditText: EditText =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_edittext_height)
        val weightEditText: EditText =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_edittext_weight)
        val muscleMassEditText: EditText =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_edittext_muscle_mass)
        val fatMassEditText: EditText =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_edittext_fat_mass)
        val upperbodyRMEditText: EditText =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_edittext_rm_upperbody)
        val lowerbodyRMEditText: EditText =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_edittext_rm_lowerbody)
        val maleButton: Button =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_button_male)
        val femaleButton: Button =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_button_female)
        val button: Button = inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_button)

        maleButton.setOnClickListener {
            gender = "남"
            it.setBackgroundResource(R.drawable.button_selected)
            femaleButton.setBackgroundResource(R.drawable.button_unselected)
        }

        femaleButton.setOnClickListener {
            gender = "여"
            it.setBackgroundResource(R.drawable.button_selected)
            maleButton.setBackgroundResource(R.drawable.button_unselected)
        }

        button.setOnClickListener {
            if (gender != "" && heightEditText.text.isNotEmpty() && weightEditText.text.isNotEmpty() && muscleMassEditText.text.isNotEmpty() && fatMassEditText.text.isNotEmpty() && upperbodyRMEditText.text.isNotEmpty() && lowerbodyRMEditText.text.isNotEmpty()) {
                height = heightEditText.text.toString().toFloat()
                weight = weightEditText.text.toString().toFloat()
                muscleMass = muscleMassEditText.text.toString().toFloat()
                fatMass = fatMassEditText.text.toString().toFloat()
                upperbodyRM = upperbodyRMEditText.text.toString().toInt()
                lowerbodyRM = lowerbodyRMEditText.text.toString().toInt()

                newUserDTO.height = height
                newUserDTO.weight = weight
                newUserDTO.muscleMass = muscleMass
                newUserDTO.fatMass = fatMass
                newUserDTO.rm_bench = upperbodyRM
                newUserDTO.rm_squat = lowerbodyRM
                newUserDTO.gender = gender

                RetrofitClient.userService.signUp(newUserDTO).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            inputPhysicalInformationDialog.dismiss()
                            activityStack.pop().finish()
                        } else Log.d("Server Access Error", "회원 가입 실패")
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }
        }

        return inputPhysicalInformationDialog
    }
}