package com.team6.routineapp.singletone

import com.team6.routineapp.dto.RoutineDTO


object Routine {
    private val myRoutines: MutableList<RoutineDTO> = mutableListOf()

    fun addRoutine(routine: RoutineDTO) {
        myRoutines.add(routine)
    }

    fun removeRoutine(routine: RoutineDTO) {
        myRoutines.remove(routine)
    }

    fun getRoutineByName(routineName: String): RoutineDTO? {
        return myRoutines.find { it.routineName == routineName }
    }

    fun getAllRoutines(): List<RoutineDTO> {
        return myRoutines.toList()
    }

    fun clearAllRoutines() {
        myRoutines.clear()
    }
}