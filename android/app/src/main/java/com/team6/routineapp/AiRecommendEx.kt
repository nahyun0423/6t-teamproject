package com.team6.routineapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.team6.routineapp.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AiRecommendEx : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_recommed_ex)

        val btn: Button =findViewById(R.id.btn1);
        val text: TextView =findViewById(R.id.textView1);
        val edit: EditText =findViewById(R.id.editText);

        btn.setOnClickListener {
            val query = edit.text.toString()
            RetrofitClient.instance.getClovaResponse(query)
                .enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.isSuccessful) {
                            text.text = response.body()
                        } else {
                            text.text = "Error Code: ${response.code()}"
                        }
                    }
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        text.text = "Error: ${t.message}"
                    }
                })
        }


    }

}