package me.ivanfenenko.falldetector

import android.util.Log
import kotlin.math.sqrt

class FallDetector {

    private var state = State.IDLE

    fun measure(x: Float, y: Float, z: Float): Boolean {
        val angularSpeed = sqrt(x * x + y * y + z * z)

        Log.d("FallDetector", "angularSpeed = $angularSpeed")

        when (state) {

            State.IDLE -> {
                if (angularSpeed < FALL_THRESHOLD) {
                    state = State.ACTIVITY
                }
            }

            State.ACTIVITY -> {
                if (angularSpeed > LAND_THRESHOLD) {
                    state = State.IDLE
                    return true
                }
            }
        }
        return false
    }

    private enum class State {
        IDLE,
        ACTIVITY
    }

    companion object {

        const val FALL_THRESHOLD = 1
        const val LAND_THRESHOLD = 12

    }

}
