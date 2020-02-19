package ru.geekbrains.kotlinnotes.ui.splash

import ru.geekbrains.kotlinnotes.data.NotesRepository
import ru.geekbrains.kotlinnotes.data.errors.NoAuthException
import ru.geekbrains.kotlinnotes.ui.base.BaseViewModel

class SplashViewModel(private val notesRepository: NotesRepository) : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        notesRepository.getCurrentUser().observeForever {
            viewStateLiveData.value = it?.let {
                SplashViewState(authenticated = true)
            } ?: let {
                SplashViewState(error = NoAuthException())
            }
        }
    }
}