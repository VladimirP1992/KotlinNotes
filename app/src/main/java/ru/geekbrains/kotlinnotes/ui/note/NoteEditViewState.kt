package ru.geekbrains.kotlinnotes.ui.note

import ru.geekbrains.kotlinnotes.data.entity.Note
import ru.geekbrains.kotlinnotes.ui.base.BaseViewState

class NoteEditViewState (note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)