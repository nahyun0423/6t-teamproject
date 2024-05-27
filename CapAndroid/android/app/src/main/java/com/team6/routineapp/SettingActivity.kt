package com.team6.routineapp

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.team6.routineapp.dto.UserDTO
import com.team6.routineapp.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingActivity : AppCompatActivity() {
    private lateinit var textWatcher: TextWatcher

    private lateinit var inputPhysicalInformationDialog: Dialog
    private lateinit var inputPhysicalInformationDialogHeightEditText: EditText
    private lateinit var inputPhysicalInformationDialogWeightEditText: EditText
    private lateinit var inputPhysicalInformationDialogMuscleMassEditText: EditText
    private lateinit var inputPhysicalInformationDialogFatMassEditText: EditText
    private lateinit var inputPhysicalInformationDialogUpperbodyRMEditText: EditText
    private lateinit var inputPhysicalInformationDialogLowerbodyRMEditText: EditText
    private lateinit var inputPhysicalInformationDialogButton: Button

    private lateinit var identifierTextView: TextView
    private lateinit var heightValueTextView: TextView
    private lateinit var weightValueTextView: TextView
    private lateinit var muscleMassValueTextView: TextView
    private lateinit var fatMassValueTextView: TextView
    private lateinit var genderValueTextView: TextView
    private lateinit var shapeValueTextView: TextView
    private lateinit var upperbodyRMValueTextView: TextView
    private lateinit var lowerbodyRMValueTextView: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        textWatcher = createTextWatcher()

        inputPhysicalInformationDialog = Dialog(this)
        inputPhysicalInformationDialog.setContentView(R.layout.dialog_input_physical_information)

        inputPhysicalInformationDialogHeightEditText =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_edittext_height)
        inputPhysicalInformationDialogWeightEditText =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_edittext_weight)
        inputPhysicalInformationDialogMuscleMassEditText =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_edittext_muscle_mass)
        inputPhysicalInformationDialogFatMassEditText =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_edittext_fat_mass)
        inputPhysicalInformationDialogUpperbodyRMEditText =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_edittext_rm_upperbody)
        inputPhysicalInformationDialogLowerbodyRMEditText =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_edittext_rm_lowerbody)

        inputPhysicalInformationDialogButton =
            inputPhysicalInformationDialog.findViewById(R.id.dialog_input_physical_information_button)

        identifierTextView = findViewById(R.id.activity_setting_textview_identifier)

        heightValueTextView = findViewById(R.id.activity_setting_textview_height_value)
        weightValueTextView = findViewById(R.id.activity_setting_textview_weight_value)
        muscleMassValueTextView = findViewById(R.id.activity_setting_textview_muscle_mass_value)
        fatMassValueTextView = findViewById(R.id.activity_setting_textview_fat_mass_value)
        genderValueTextView = findViewById(R.id.activity_setting_textview_gender_value)
        shapeValueTextView = findViewById(R.id.activity_setting_textview_shape_value)
        upperbodyRMValueTextView = findViewById(R.id.activity_setting_textview_upperbody_rm_value)
        lowerbodyRMValueTextView = findViewById(R.id.activity_setting_textview_lowerbody_rm_value)

        button = findViewById(R.id.activity_setting_button)

        heightValueTextView.text = userDTO.height.toString()
        weightValueTextView.text = userDTO.weight.toString()
        muscleMassValueTextView.text = userDTO.muscleMass.toString()
        fatMassValueTextView.text = userDTO.fatMass.toString()
        genderValueTextView.text = userDTO.gender.toString()
        shapeValueTextView.text = userDTO.shape.toString()
        upperbodyRMValueTextView.text = userDTO.rm_bench.toString()
        lowerbodyRMValueTextView.text = userDTO.rm_squat.toString()

        //db에서 userid나 프로필 사진 받아 적용

        identifierTextView.text = userDTO.userId

        generateDialog()

        button.setOnClickListener {
            inputPhysicalInformationDialog.show()
        }
    }

    private fun areAllFieldsFilled(): Boolean {
        return inputPhysicalInformationDialogHeightEditText.text.isNotEmpty() &&
                inputPhysicalInformationDialogWeightEditText.text.isNotEmpty() &&
                inputPhysicalInformationDialogMuscleMassEditText.text.isNotEmpty() &&
                inputPhysicalInformationDialogFatMassEditText.text.isNotEmpty() &&
                inputPhysicalInformationDialogUpperbodyRMEditText.text.isNotEmpty() &&
                inputPhysicalInformationDialogLowerbodyRMEditText.text.isNotEmpty()
    }

    private fun createTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                inputPhysicalInformationDialogButton.isEnabled = areAllFieldsFilled()
                if (inputPhysicalInformationDialogButton.isEnabled) {
                    inputPhysicalInformationDialogButton.text = "완료"
                    inputPhysicalInformationDialogButton.setBackgroundResource(R.drawable.button_no)
                }
                else {
                    inputPhysicalInformationDialogButton.text = "모든 정보를 입력해주세요"
                    inputPhysicalInformationDialogButton.setBackgroundResource(R.drawable.button_no_gray)
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }
    }


    private fun generateDialog(): Dialog {
        inputPhysicalInformationDialogHeightEditText.addTextChangedListener(textWatcher)
        inputPhysicalInformationDialogWeightEditText.addTextChangedListener(textWatcher)
        inputPhysicalInformationDialogMuscleMassEditText.addTextChangedListener(textWatcher)
        inputPhysicalInformationDialogFatMassEditText.addTextChangedListener(textWatcher)
        inputPhysicalInformationDialogUpperbodyRMEditText.addTextChangedListener(textWatcher)
        inputPhysicalInformationDialogLowerbodyRMEditText.addTextChangedListener(textWatcher)

        inputPhysicalInformationDialog = Dialog(this)
        inputPhysicalInformationDialog.setContentView(R.layout.dialog_input_physical_information)

        var height: Float
        var weight: Float
        var muscleMass: Float
        var fatMass: Float
        var gender = "남"
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

                Log.d("Test", upperbodyRM.toString())

                userDTO.height = height
                userDTO.weight = weight
                userDTO.muscleMass = muscleMass
                userDTO.fatMass = fatMass
                userDTO.rm_bench = upperbodyRM
                userDTO.rm_squat = lowerbodyRM
                userDTO.gender = gender

                Log.d("Test", userDTO.RM_bench.toString())

                RetrofitClient.userService.editUser(userDTO).enqueue(object : Callback<UserDTO> {
                    override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                        if (response.isSuccessful) {
                            userDTO = response.body()!!
                            heightValueTextView.text = userDTO.height.toString()
                            weightValueTextView.text = userDTO.weight.toString()
                            muscleMassValueTextView.text = userDTO.muscleMass.toString()
                            fatMassValueTextView.text = userDTO.fatMass.toString()
                            genderValueTextView.text = userDTO.gender.toString()
                            shapeValueTextView.text = userDTO.shape.toString()
                            upperbodyRMValueTextView.text = userDTO.RM_bench.toString()
                            lowerbodyRMValueTextView.text = userDTO.RM_squat.toString()

                            Log.d("Test", userDTO.RM_bench.toString())
                            Log.d("Test", userDTO.height.toString())
                            Log.d("Test", userDTO.shape.toString())
                            inputPhysicalInformationDialog.dismiss()
                        }
                    }

                    override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            }
        }

        return inputPhysicalInformationDialog
    }
}