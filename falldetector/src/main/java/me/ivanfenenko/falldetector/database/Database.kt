package me.ivanfenenko.falldetector.database

import me.ivanfenenko.falldetector.model.FallRecord

interface Database {

    fun addRecord(record: FallRecord)

    fun getAllRecords(): List<FallRecord>

    fun clearData()

}
