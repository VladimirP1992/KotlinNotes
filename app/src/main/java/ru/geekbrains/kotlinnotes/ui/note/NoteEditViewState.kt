package ru.geekbrains.kotlinnotes.ui.note

import ru.geekbrains.kotlinnotes.data.entity.Note
import ru.geekbrains.kotlinnotes.ui.base.BaseViewState

class NoteEditViewState(data: Data = Data(), error: Throwable? = null) : BaseViewState<NoteEditViewState.Data>(data, error) {
    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}