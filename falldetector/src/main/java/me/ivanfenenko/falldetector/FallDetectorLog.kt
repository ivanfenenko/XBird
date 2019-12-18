package me.ivanfenenko.falldetector

import android.content.Context
import me.ivanfenenko.falldetector.di.component
import me.ivanfenenko.falldetector.model.FallRecord

class FallDetectorLog {

    companion object {

        fun getData(context: Context): List<FallRecord> = component(context).database().getAllRecords()

    }

}
