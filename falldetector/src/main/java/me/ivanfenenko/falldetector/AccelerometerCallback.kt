package me.ivanfenenko.falldetector

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.Log
import me.ivanfenenko.falldetector.database.Database
import me.ivanfenenko.falldetector.model.FallRecord
import javax.inject.Inject

class AccelerometerCallback @Inject constructor(
    private val fallDetector: FallDetector,
    private val database: Database
) : SensorEventListener {

    var fallDetectionCallback: () -> Unit = {}

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            Log.d("AccelerometerCallback", "Fall detected $x $y $z")
            when (val record = fallDetector.measure(x, y, z)) {
                is FallRecord -> {
                    fallDetectionCallback.invoke()
                    database.addRecord(record)
                }
            }
        }
    }

}
