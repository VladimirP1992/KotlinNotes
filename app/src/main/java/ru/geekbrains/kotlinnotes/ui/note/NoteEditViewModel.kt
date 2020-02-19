package ru.geekbrains.kotlinnotes.ui.note

import ru.geekbrains.kotlinnotes.data.NotesRepository
import ru.geekbrains.kotlinnotes.data.entity.Note
import ru.geekbrains.kotlinnotes.data.model.NoteResult
import ru.geekbrains.kotlinnotes.ui.base.BaseViewModel

class NoteEditViewModel (private val notesRepository: NotesRepository) : BaseViewModel<NoteEditViewState.Data, NoteEditViewState>() {

    private val pendingNote: Note?
        get() = viewStateLiveData.value?.data?.note

    fun save(note: Note) {
        viewStateLiveData.value = NoteEditViewState(NoteEditViewState.Data(note = note))
    }

    fun loadNote(noteId: String) {
        notesRepository.getNoteById(noteId).observeForever { result ->
            result?.let {
                viewStateLiveData.value = when (result) {
                    is NoteResult.Success<*> -> NoteEditViewState(NoteEditViewState.Data(note = result.data as Note))
                    is NoteResult.Error -> NoteEditViewState(error = result.error)
                }
            }
        }
    }

    fun deleteNote() {
        pendingNote?.let {
            notesRepository.deleteNote(it.id).observeForever { result ->
                viewStateLiveData.value = when (result) {
                    is NoteResult.Success<*> -> NoteEditViewState(NoteEditViewState.Data(isDeleted = true))
                    is NoteResult.Error -> NoteEditViewState(error = result.error)
                }
            }
        }
    }

    override fun onCleared() {
        pendingNote?.let {
            notesRepository.saveNote(it)
        }
    }
}