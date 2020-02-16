package ru.geekbrains.kotlinnotes.ui.splash

import ru.geekbrains.kotlinnotes.data.NotesRepository
import ru.geekbrains.kotlinnotes.data.errors.NoAuthException
import ru.geekbrains.kotlinnotes.ui.base.BaseViewModel

class SplashViewModel() : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        NotesRepository.getCurrentUser().observeForever {                                                                                                                                                                                                                           //Я копипастил код с урока и не заметил эту надпись
            viewStateLiveData.value = it?.let {
                SplashViewState(authenticated = true)
            } ?: let {
                SplashViewState(error = NoAuthException())
            }
        }
    }
}