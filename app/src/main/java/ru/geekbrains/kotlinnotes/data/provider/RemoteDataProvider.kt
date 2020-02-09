package ru.geekbrains.kotlinnotes.data.provider

import androidx.lifecycle.LiveData
import ru.geekbrains.kotlinnotes.data.entity.Note
import ru.geekbrains.kotlinnotes.data.model.NoteResult

interface RemoteDataProvider {
    fun subsrcibeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
}