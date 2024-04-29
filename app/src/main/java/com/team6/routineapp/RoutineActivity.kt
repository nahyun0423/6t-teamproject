package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class RoutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine)

        val toCreateRoutineActivity = Intent(this, CreateRoutineActivity::class.java);
        findViewById<Button>(R.id.routineActivity_button).setOnClickListener {
            startActivity(toCreateRoutineActivity);
        }

        val routines = findViewById<LinearLayout>(R.id.routineActivity_LinearLayout)
        generateRoutineList(routines)

    }

    private fun generateRoutineList(routines: LinearLayout) {
        for (i in 1..9) {

            val routine = layoutInflater.inflate(R.layout.routine, null)

            routine.findViewById<TextView>(R.id.routineName).text = "초보자 상체 운동 ${i}"
            routine.findViewById<TextView>(R.id.exerciseTarget).text = "가슴 등"
            routine.findViewById<TextView>(R.id.exercise1).text = "벤치 프레스"
            routine.findViewById<TextView>(R.id.exercise2).text = "랫 풀 다운"
            routine.findViewById<ImageView>(R.id.image)
                .setImageResource(R.drawable.benchpress_image)
            routines.addView(routine)
            val intent = Intent(this, ExplainRoutine::class.java)
            intent.putExtra("name", "초보자 상체 운동 ${i}")
            routine.setOnClickListener(View.OnClickListener {
                startActivity(intent)
            })
            //마진 적용
            val size = getResources().getDimensionPixelSize(R.dimen.bottom_margin)
            val margin = MarginLayoutParams(routine.getLayoutParams())
            margin.setMargins(0, 0, 0, size)
            routine.setLayoutParams(LinearLayout.LayoutParams(margin))
        }
    }
}