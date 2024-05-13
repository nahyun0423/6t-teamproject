package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.team6.routineapp.fitness.Routine
import com.team6.routineapp.utility.convertFromDpToPx
import com.team6.routineapp.utility.getClassExtra
import kotlin.math.log


class RoutineActivity : AppCompatActivity() {
    companion object {
        var routines = arrayOf<Routine>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine)

        val intentToCreateRoutineActivity = Intent(this, CreateRoutineActivity::class.java);

        findViewById<Button>(R.id.activity_routine_button).setOnClickListener {
            startActivity(intentToCreateRoutineActivity)
        }

        val routinesLayout = findViewById<LinearLayout>(R.id.activity_routine_layout)

        var routine = intent.getClassExtra("routine", Routine::class.java)

        if (routine != null) {
            routines += (routine)
        }

        for (routine in routines) {
            routinesLayout.addView(generateRoutineView(routine))
        }
    }

    private fun generateRoutineView(routine: Routine): View {
        val layoutParameters = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParameters.setMargins(0, 0, 0, convertFromDpToPx(20))
        val intent = Intent(this, RoutineInformationActivity::class.java)
        val routineName = routine.name
        intent.putExtra("name", routineName)

        val routineView = layoutInflater.inflate(R.layout.view_routine, null)
        val routineViewtrainingsLayout =
            routineView.findViewById<LinearLayout>(R.id.view_routine_layout_trainings)
        var routineViewTrainingsTextView: TextView

        routineView.layoutParams = layoutParameters

        routineView.findViewById<TextView>(R.id.view_routine_textview_name).text = routineName
        routineView.findViewById<TextView>(R.id.view_routine_textview_part).text =
            routine.trainings[0]!!.exercise.part
        routineView.findViewById<ImageView>(R.id.view_routine_imageview)
            .setImageResource(R.drawable.benchpress_image)

        for (training in routine.trainings) {
            routineViewTrainingsTextView = TextView(this)
            routineViewTrainingsTextView.setText(training!!.exercise.name)
            routineViewtrainingsLayout.addView(routineViewTrainingsTextView)
        }

        routineView.setOnClickListener {
            startActivity(intent)
        }

        return routineView
    }
}