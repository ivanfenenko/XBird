package me.ivanfenenko.falldetector

import android.util.Log
import me.ivanfenenko.falldetector.model.FallRecord
import java.util.Date
import javax.inject.Inject
import kotlin.math.sqrt

class FallDetector @Inject constructor() {

    private var fallStarted: Date = Date()
    private var fallEnded: Date = Date()

    private var state = State.IDLE

    fun measure(x: Float, y: Float, z: Float): FallRecord? {
        val angularSpeed = sqrt(x * x + y * y + z * z)

        Log.d("FallDetector", "angularSpeed = $angularSpeed")

        when (state) {

            State.IDLE -> {
                if (angularSpeed < FALL_THRESHOLD) {
                    state = State.ACTIVITY
                    fallStartDetected()
                }
            }

            State.ACTIVITY -> {
                if (angularSpeed > LAND_THRESHOLD) {
                    state = State.IDLE
                    fallEndDetected()
                    return getFallRecordObject()
                }
            }
        }
        return null
    }

    private fun fallStartDetected() {
        fallStarted = Date()
    }

    private fun fallEndDetected() {
        fallEnded = Date()
    }

    private fun getFallRecordObject() = FallRecord(Date(), fallEnded.time - fallStarted.time)

    private enum class State {
        IDLE,
        ACTIVITY
    }

    companion object {

        const val FALL_THRESHOLD = 1
        const val LAND_THRESHOLD = 10

    }

}
