package me.ivanfenenko.falldetector.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.ivanfenenko.falldetector.database.DatabaseImpl

@Module
class ProvidingModule {

    @Provides
    @Reusable
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(DatabaseImpl.PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

    @Provides
    @Reusable
    fun providesGson() = Gson()

}
