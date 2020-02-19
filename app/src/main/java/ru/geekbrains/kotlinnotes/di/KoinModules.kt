package ru.geekbrains.kotlinnotes.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.ext.koin.viewModel

import org.koin.dsl.module.module
import ru.geekbrains.kotlinnotes.data.NotesRepository
import ru.geekbrains.kotlinnotes.data.provider.FireStoreProvider
import ru.geekbrains.kotlinnotes.data.provider.RemoteDataProvider
import ru.geekbrains.kotlinnotes.ui.main.MainViewModel
import ru.geekbrains.kotlinnotes.ui.note.NoteEditViewModel
import ru.geekbrains.kotlinnotes.ui.splash.SplashViewModel

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FireStoreProvider(get(), get()) } bind RemoteDataProvider::class
    single { NotesRepository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteEditViewModel(get()) }
}