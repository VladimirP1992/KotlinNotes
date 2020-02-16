package ru.geekbrains.kotlinnotes.ui.note

import androidx.lifecycle.Observer
import ru.geekbrains.kotlinnotes.data.NotesRepository
import ru.geekbrains.kotlinnotes.data.entity.Note
import ru.geekbrains.kotlinnotes.data.model.NoteResult
import ru.geekbrains.kotlinnotes.ui.base.BaseViewModel

class NoteEditViewModel: BaseViewModel<Note?, NoteEditViewState>() {

    init {
        viewStateLiveData.value = NoteEditViewState()
    }

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    fun loadNote(noteId: String) {
        NotesRepository.getNoteById(noteId).observeForever(object : Observer<NoteResult> {
            override fun onChanged(t: NoteResult?) {
                t ?: return
                when (t) {
                    is NoteResult.Success<*> -> {
                        viewStateLiveData.value = NoteEditViewState(note = t.data as Note)
                    }
                    is NoteResult.Error -> {
                        viewStateLiveData.value = NoteEditViewState(error = t.error)
                    }
                }
            }
        })
    }

    override fun onCleared() {
        pendingNote?.let {
            NotesRepository.saveNote(it)
        }
    }
}