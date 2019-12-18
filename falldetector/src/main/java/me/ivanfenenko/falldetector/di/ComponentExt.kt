package me.ivanfenenko.falldetector.di

import android.content.Context

fun component(context: Context) = DaggerLibraryComponent
    .builder()
    .context(context)
    .build()
