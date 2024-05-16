package com.team6.routineapp.fitness

import java.io.Serializable

class Exercise(var name: String, var part: String, var tool: String) : Serializable {}

open class Training(var exercise: Exercise, var set: Int, var numberOfTimes: Int) : Serializable {
    open fun getDetail(): String {
        return String.format("%d회, %d세트", numberOfTimes, set);
    }
}

class WeightTraining(exercise: Exercise, set: Int, numberOfTimes: Int, var weight: Int) :
    Training(exercise, set, numberOfTimes) {
    override fun getDetail(): String {
        return String.format("%dkg, ", weight) + super.getDetail()
    }
}

class Routine(var name: String, var trainings: Array<Training?>) : Serializable {}