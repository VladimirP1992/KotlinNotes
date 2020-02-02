package ru.geekbrains.kotlinnotes.data

import ru.geekbrains.kotlinnotes.data.entity.Note

class NotesRepository {
    val notes: List<Note> = listOf(
        Note("Note 1 title", "Note 1 text", 0xfff06292.toInt()),
        Note("Note 2 title", "Note 2 text", 0xff9575cd.toInt()),
        Note("Note 3 title", "Note 3 text", 0xff64b5f6.toInt()),
        Note("Note 4 title", "Note 4 text", 0xff4db6ac.toInt()),
        Note("Note 5 title", "Note 5 text", 0xffb2ff59.toInt()),
        Note("Note 6 title", "Note 6 text", 0xffffeb3b.toInt())
    )
}