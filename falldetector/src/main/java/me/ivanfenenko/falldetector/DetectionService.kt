package me.ivanfenenko.falldetector

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.IBinder
import me.ivanfenenko.falldetector.di.component
import javax.inject.Inject

class DetectionService : Service() {

    @Inject
    lateinit var accelerometerCallback: AccelerometerCallback

    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null

    override fun onBind(intent: Intent): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        component(this).inject(this)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (intent.action == ACTION_START) {
            registerService()
        } else {
            unregisterService()
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        sensorManager?.unregisterListener(accelerometerCallback)
        sensorManager = null
        accelerometer = null
        super.onDestroy()
    }

    private fun registerService() {
        showStickyNotification()
        startDetection()
    }

    private fun unregisterService() {
        stopForeground(true)
        stopSelf()
    }

    private fun showStickyNotification() {
        createNotificationChannel()
        startForeground()
    }

    private fun startDetection() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager?.registerListener(accelerometerCallback, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        accelerometerCallback.fallDetectionCallback = {
            showFallNotification()
        }
    }

    companion object {

        const val CHANNEL_ID = "ForegroundServiceChannel"

        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"

    }

}
