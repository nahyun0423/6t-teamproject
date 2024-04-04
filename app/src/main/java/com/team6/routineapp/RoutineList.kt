package com.team6.routineapp

import android.os.Bundle
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class RoutineList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.myroutine_ui) //xml과 연결

        val linearLayout: LinearLayout = findViewById(R.id.linearLayout)

        //루틴 리스트 동적 생성
        generateRoutineList(linearLayout)

    }
    private fun generateRoutineList(linearLayout: LinearLayout) {
        for (i in 1..9) {

            val newView = layoutInflater.inflate(R.layout.routine, null)

            newView.findViewById<TextView>(R.id.routineName).text = "초보자 상체 운동"
            newView.findViewById<TextView>(R.id.exerciseTarget).text = "가슴 등"
            newView.findViewById<TextView>(R.id.exercise1).text = "벤치 프레스"
            newView.findViewById<TextView>(R.id.exercise2).text = "랫 풀 다운"
            newView.findViewById<ImageView>(R.id.image).setImageResource(R.drawable.benchpress_image)
            linearLayout.addView(newView)

            //마진 적용
            val size = getResources().getDimensionPixelSize(R.dimen.bottom_margin)
            val margin = MarginLayoutParams(newView.getLayoutParams())
            margin.setMargins(0, 0, 0, size)
            newView.setLayoutParams(LinearLayout.LayoutParams(margin))
        }
    }
}