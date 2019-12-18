package me.ivanfenenko.falldetector.model

import java.util.Date

data class FallRecord(
    val timestamp: Date,
    val duration: Long
)
