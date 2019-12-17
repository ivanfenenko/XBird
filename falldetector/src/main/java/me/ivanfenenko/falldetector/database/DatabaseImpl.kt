package me.ivanfenenko.falldetector.database

import android.content.SharedPreferences

class DatabaseImpl(sharedPreferences: SharedPreferences) : Database {

    override fun addRecord(record: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllRecords(): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        const val PREFERENCES_KEY = "me.ivanfenenko.falldetector.database"

    }

}
