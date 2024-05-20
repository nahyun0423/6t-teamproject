package com.team6.routineapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.team6.routineapp.fitness.*
import com.team6.routineapp.utility.*


class RoutineActivity : AppCompatActivity() {
    companion object {
        var routines: Array<Routine> = arrayOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine)

        val intentToCreateRoutineActivity = Intent(this, CreateRoutineActivity::class.java);

        val searchView: SearchView = findViewById(R.id.activity_routine_searchview)
        val routinesLayout = findViewById<LinearLayout>(R.id.activity_routine_layout)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query == null) return false

                for(routineView in routinesLayout.children) routineView.visibility = View.GONE
                for(routineView in routinesLayout.children) {
                    val routine = routineView.tag as Routine

                    if (routine.name.contains(query)) {
                        routineView.visibility = View.VISIBLE
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        findViewById<Button>(R.id.activity_routine_button).setOnClickListener {
            startActivity(intentToCreateRoutineActivity)
        }

        var routine = intent.getClassExtra("routine", Routine::class.java)

        if (routine != null) {
            routines += (routine)
        }

        for (routine in routines) {
            routinesLayout.addView(generateRoutineView(routine))
        }
    }

    private fun generateTrainingView(training: Training): TextView {
        val routineViewTrainingsTextView: TextView = TextView(this)

        routineViewTrainingsTextView.text = training!!.exercise.name

        return routineViewTrainingsTextView
    }

    private fun generateTrainingsView(trainings: Array<Training?>, parent: LinearLayout) {
        for (training in trainings) {
            parent.addView(generateTrainingView(training!!))
        }
    }

    private fun generateRoutineView(routine: Routine): View {
        val routineName = routine.name
        val layoutParameters = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        val intentToRoutineInformationActivity =
            Intent(this, RoutineInformationActivity::class.java)
        intentToRoutineInformationActivity.putExtra("name", routineName)

        val routineView = layoutInflater.inflate(R.layout.view_routine, null)
        val routineViewTrainingsLayout =
            routineView.findViewById<LinearLayout>(R.id.view_routine_layout_trainings)

        layoutParameters.setMargins(0, 0, 0, convertFromDpToPx(20))

        routineView.layoutParams = layoutParameters
        routineView.tag = routine
        routineView.findViewById<TextView>(R.id.view_routine_textview_name).text = routineName
        routineView.findViewById<TextView>(R.id.view_routine_textview_part).text =
            routine.trainings[0]!!.exercise.part
        routineView.findViewById<ImageView>(R.id.view_routine_imageview)
            .setImageResource(R.drawable.benchpress_image)
        routineView.setOnClickListener {
            startActivity(intentToRoutineInformationActivity)
        }

        generateTrainingsView(routine.trainings, routineViewTrainingsLayout)

        return routineView
    }
}