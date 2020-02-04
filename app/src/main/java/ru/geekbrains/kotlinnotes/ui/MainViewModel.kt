package ru.geekbrains.kotlinnotes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.kotlinnotes.data.NotesRepository

class MainViewModel : ViewModel() {
    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        viewStateLiveData.value =
            MainViewState(NotesRepository.notes)
    }

    fun getViewStateLiveData() : LiveData<MainViewState> = viewStateLiveData
}