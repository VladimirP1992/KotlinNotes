package ru.geekbrains.kotlinnotes.data

import ru.geekbrains.kotlinnotes.data.entity.Note

import ru.geekbrains.kotlinnotes.data.provider.RemoteDataProvider


class NotesRepository(val remoteProvider: RemoteDataProvider) {
    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser() = remoteProvider.getCurrentUser()
    fun deleteNote(id: String) = remoteProvider.deleteNote(id)
}
