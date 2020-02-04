package ru.geekbrains.kotlinnotes.ui

import androidx.lifecycle.ViewModel
import ru.geekbrains.kotlinnotes.data.NotesRepository
import ru.geekbrains.kotlinnotes.data.entity.Note

class NoteEditViewModel: ViewModel() {
    private var pendingNote: Note? = null

    fun save(note: Note){
        pendingNote = note
    }

    override fun onCleared(){
        pendingNote?.let {
            NotesRepository.saveNote(it)
        }
    }

}