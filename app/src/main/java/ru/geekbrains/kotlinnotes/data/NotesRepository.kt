package ru.geekbrains.kotlinnotes.data

import ru.geekbrains.kotlinnotes.data.entity.Note
import ru.geekbrains.kotlinnotes.data.provider.FireStoreProvider
import ru.geekbrains.kotlinnotes.data.provider.RemoteDataProvider


object NotesRepository {

    private val remoteProvider: RemoteDataProvider = FireStoreProvider()

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser() = remoteProvider.getCurrentUser()
}
