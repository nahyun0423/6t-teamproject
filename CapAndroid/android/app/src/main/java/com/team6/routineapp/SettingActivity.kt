package com.team6.routineapp

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.team6.routineapp.fitness.UserInfo

class SettingActivity : AppCompatActivity() {

    private lateinit var userInfo : UserInfo

    private lateinit var editUserInfoButton : Button

    private lateinit var inputUserInfoDialog: Dialog
    private lateinit var inputUserInfoDialogHeightEditText: EditText
    private lateinit var inputUserInfoDialogWeightEditText: EditText
    private lateinit var inputUserInfoDialogMuscleEditText: EditText
    private lateinit var inputUserInfoDialogFatEditText: EditText
    private lateinit var inputUserInfoDialogButton: Button

    private lateinit var userId: TextView
    private lateinit var userWeight: TextView
    private lateinit var userMuscle: TextView
    private lateinit var userFat: TextView

    val textWatcher = createTextWatcher()

    override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_info)

        inputUserInfoDialog = Dialog(this)
        inputUserInfoDialog.setContentView(R.layout.dialog_input_physical_information)
        inputUserInfoDialogHeightEditText = inputUserInfoDialog.findViewById<EditText>(R.id.dialog_input_user_information_edittext_height)
        inputUserInfoDialogWeightEditText = inputUserInfoDialog.findViewById<EditText>(R.id.dialog_input_physical_information_edittext_user_weight)
        inputUserInfoDialogMuscleEditText = inputUserInfoDialog.findViewById<EditText>(R.id.dialog_input_physical_information_edittext_user_muscle)
        inputUserInfoDialogFatEditText = inputUserInfoDialog.findViewById<EditText>(R.id.dialog_input_user_information_edittext_user_fat)
        inputUserInfoDialogButton = inputUserInfoDialog.findViewById<Button>(R.id.dialog_input_user_information_button)

        userId = findViewById<TextView>(R.id.activity_setting_info_textView_userId)
        userWeight = findViewById<TextView>(R.id.textView_user_weight)
        userMuscle = findViewById<TextView>(R.id.textView_user_muscle)
        userFat = findViewById<TextView>(R.id.textView_user_fat)

        editUserInfoButton = findViewById<Button>(R.id.activity_setting_info_edit_button)

        //db에서 userid나 프로필 사진 받아 적용


        //dialog 부분
        inputUserInfoDialogHeightEditText.addTextChangedListener(textWatcher)
        inputUserInfoDialogWeightEditText.addTextChangedListener(textWatcher)
        inputUserInfoDialogMuscleEditText.addTextChangedListener(textWatcher)
        inputUserInfoDialogFatEditText.addTextChangedListener(textWatcher)

        editUserInfoButton.setOnClickListener {
            inputUserInfoDialog.show()

            inputUserInfoDialogButton.setOnClickListener {
                userInfo = UserInfo(inputUserInfoDialogHeightEditText.text.toString().toInt(),
                    inputUserInfoDialogWeightEditText.text.toString().toInt(),
                    inputUserInfoDialogMuscleEditText.text.toString().toInt(),
                    inputUserInfoDialogFatEditText.text.toString().toInt())

                //UserInfo를 EditText값을 db에 저장

                userWeight.setText(userInfo.weight.toString())
                userMuscle.setText(userInfo.muscle.toString())
                userFat.setText(userInfo.fat.toString())
                inputUserInfoDialog.dismiss()
           }
        }
    }

    private fun areAllFieldsFilled(): Boolean {
        return inputUserInfoDialogHeightEditText.text.isNotEmpty() &&
                inputUserInfoDialogWeightEditText.text.isNotEmpty() &&
                inputUserInfoDialogMuscleEditText.text.isNotEmpty() &&
                inputUserInfoDialogFatEditText.text.isNotEmpty()
    }

    private fun createTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                inputUserInfoDialogButton.isEnabled = areAllFieldsFilled()
                if(inputUserInfoDialogButton.isEnabled)
                    inputUserInfoDialogButton.setText("완료")
                else
                    inputUserInfoDialogButton.setText("모든 정보를 입력해주세요")
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }
    }
}