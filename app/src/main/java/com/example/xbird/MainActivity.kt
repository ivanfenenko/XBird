package com.example.xbird

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.button_start
import kotlinx.android.synthetic.main.activity_main.button_stop
import me.ivanfenenko.falldetector.startDetectionService
import me.ivanfenenko.falldetector.stopDetectionService

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_start.setOnClickListener {
            startDetectionService()
        }

        button_stop.setOnClickListener {
            stopDetectionService()
        }
    }

}
