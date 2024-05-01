package com.team6.routineapp.utility

import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.convertFromDpToPx(value: Int): Int {
    var displayMetrics = resources.displayMetrics
    return Math.round(value * displayMetrics.density)
}