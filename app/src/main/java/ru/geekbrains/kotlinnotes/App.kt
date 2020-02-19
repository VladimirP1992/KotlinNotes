package ru.geekbrains.kotlinnotes

import android.app.Application
import org.koin.android.ext.android.startKoin
import ru.geekbrains.kotlinnotes.di.appModule
import ru.geekbrains.kotlinnotes.di.mainModule
import ru.geekbrains.kotlinnotes.di.noteModule
import ru.geekbrains.kotlinnotes.di.splashModule

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
    }
}