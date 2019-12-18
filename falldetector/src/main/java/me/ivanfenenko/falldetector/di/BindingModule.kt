package me.ivanfenenko.falldetector.di

import dagger.Binds
import dagger.Module
import dagger.Reusable
import me.ivanfenenko.falldetector.database.Database
import me.ivanfenenko.falldetector.database.DatabaseImpl

@Module
interface BindingModule {

    @Binds
    @Reusable
    fun bindsDatabase(databaseImpl: DatabaseImpl): Database

}
