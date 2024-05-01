package com.team6.routineapp.fitness

import java.io.Serializable

open class Exercise(
    var name: String,
    var set: Int,
    var numberOfTimes: Int,
    var part: String,
    var tool: String
) : Serializable {
    open fun getDetail(): String {
        return String.format("%d회, %d세트", numberOfTimes, set);
    }
}

class WeightTraining(
    name: String,
    set: Int,
    numberOfTimes: Int,
    part: String,
    tool: String,
    var weight: Int
) :
    Exercise(name, set, numberOfTimes, part, tool) {
    override fun getDetail(): String {
        return String.format("%dkg, ", weight) + super.getDetail()
    }
}