package me.ivanfenenko.falldetector.database

interface Database {

    fun addRecord(record: String)

    fun getAllRecords(): List<String>

    fun clearData()

}
