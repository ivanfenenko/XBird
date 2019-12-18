package me.ivanfenenko.falldetector.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.ivanfenenko.falldetector.DetectionService
import me.ivanfenenko.falldetector.database.Database
import javax.inject.Singleton

@Singleton
@Component(modules = [BindingModule::class, ProvidingModule::class])
interface LibraryComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): LibraryComponent

    }

    fun inject(service: DetectionService)

    fun database(): Database

}
