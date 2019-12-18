package me.ivanfenenko.falldetector.database

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import me.ivanfenenko.falldetector.model.FallRecord
import me.ivanfenenko.falldetector.model.FallRecords
import javax.inject.Inject

class DatabaseImpl @Inject constructor(
    private val gson: Gson,
    private val sharedPreferences: SharedPreferences
) : Database {

    override fun addRecord(record: FallRecord) {
        val recordsJson = sharedPreferences.getString(PREFERENCES_RECORDS, "").orEmpty()
        var records = try {
            gson.fromJson(recordsJson, FallRecords::class.java)
        } catch (ex: JsonSyntaxException) {
            FallRecords(mutableListOf())
        }
        if (records == null) records = FallRecords(mutableListOf())
        records.list.add(record)
        sharedPreferences.edit().putString(PREFERENCES_RECORDS, gson.toJson(records)).apply()
    }

    override fun getAllRecords(): List<FallRecord> {
        val recordsJson = sharedPreferences.getString(PREFERENCES_RECORDS, "").orEmpty()
        val records = try {
            gson.fromJson(recordsJson, FallRecords::class.java)
        } catch (ex: JsonSyntaxException) {
            FallRecords(mutableListOf())
        }
        return records?.list ?: emptyList()
    }

    override fun clearData() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {

        const val PREFERENCES_KEY = "me.ivanfenenko.falldetector.database"

        private const val PREFERENCES_RECORDS = "me.ivanfenenko.falldetector.database.records"

    }

}
