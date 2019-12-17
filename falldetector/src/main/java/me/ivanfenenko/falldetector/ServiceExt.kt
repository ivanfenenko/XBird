package me.ivanfenenko.falldetector

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

fun Context.startDetectionService() {
    val startIntent = Intent(this, DetectionService::class.java)
    startIntent.action = DetectionService.ACTION_START
    startService(startIntent)
}

fun Context.stopDetectionService() {
    val stopIntent = Intent(this, DetectionService::class.java)
    stopIntent.action = DetectionService.ACTION_STOP
    startService(stopIntent)
}

fun DetectionService.startForeground() {
    val startActivityIntent = packageManager.getLaunchIntentForPackage(packageName)
    val startActivityPendingIntent = android.app.PendingIntent.getActivity(
        this,
        0,
        startActivityIntent,
        0
    )

    val stopServiceIntent = Intent(this, DetectionService::class.java)
    stopServiceIntent.action = DetectionService.ACTION_STOP
    val stopServicePendingIntent = android.app.PendingIntent.getService(
        this,
        0,
        stopServiceIntent,
        0
    )
    val stopAction = NotificationCompat.Action.Builder(
        R.drawable.ic_stop_black_24dp,
        "Stop",
        stopServicePendingIntent
    ).build()

    val notification = NotificationCompat.Builder(this, DetectionService.CHANNEL_ID)
        .setContentTitle("XBird Service")
        .setContentText("Fall detection runiing in background")
        .addAction(stopAction)
        .setSmallIcon(R.drawable.ic_running_black_24dp)
        .setContentIntent(startActivityPendingIntent)
        .build()

    startForeground(1, notification)
}

fun DetectionService.createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val serviceChannel = NotificationChannel(
            DetectionService.CHANNEL_ID,
            "Foreground Service Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        serviceChannel.enableVibration(true)
        val manager = getSystemService(NotificationManager::class.java)
        manager?.createNotificationChannel(serviceChannel)
    }
}

fun DetectionService.showFallNotification() {
    val notification = NotificationCompat.Builder(this, DetectionService.CHANNEL_ID)
        .setContentTitle("XBird Service")
        .setContentText("Fall Detected!")
        .setSmallIcon(R.drawable.ic_warning_black_24dp)
        .build()

    with(NotificationManagerCompat.from(this)) {
        notify(Random.nextInt(), notification)
    }
}
