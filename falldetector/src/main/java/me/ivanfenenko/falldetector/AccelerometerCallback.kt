package me.ivanfenenko.falldetector

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.Log

class AccelerometerCallback : SensorEventListener {

    private lateinit var fallDetector: FallDetector

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            when (fallDetector.measure(x, y, z)) {
                true -> {
                    Log.d("AccelerometerCallback", "Fall detected $x $y $z")
                }
            }
        }
    }

}
