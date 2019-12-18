package com.example.xbird

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.button_start
import kotlinx.android.synthetic.main.activity_main.button_stop
import kotlinx.android.synthetic.main.activity_main.output_textview
import me.ivanfenenko.falldetector.FallDetectorLog
import me.ivanfenenko.falldetector.startDetectionService
import me.ivanfenenko.falldetector.stopDetectionService
import java.text.DateFormat
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_start.setOnClickListener {
            startDetectionService()
        }

        button_stop.setOnClickListener {
            stopDetectionService()
        }

        checkDatabase()
    }

    private fun checkDatabase() {
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
            .timeInterval()
            .subscribeOn(Schedulers.io())
            .switchMapSingle {
                Single.just(FallDetectorLog.getData(this@MainActivity))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { record ->
                val resultString = record.joinToString(separator = "\n", transform = {
                    "${it.duration}ms || ${DateFormat.getDateTimeInstance().format(it.timestamp)}"
                })
                output_textview.text = resultString
            }
            .also { compositeDisposable.add(it) }
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }

}
