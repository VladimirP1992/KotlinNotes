package ru.geekbrains.kotlinnotes.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.geekbrains.kotlinnotes.data.entity.Note
import java.util.*

object NotesRepository {
    private val notesLiveData = MutableLiveData<List<Note>>()

    private val notes: MutableList<Note> = mutableListOf(
        Note(
            UUID.randomUUID().toString(),
            "Note 1 title", "Note 1 text",
            Note.Color.WHITE
        ),
        Note(
            UUID.randomUUID().toString(),
            "Note 2 title", "Note 2 text",
            Note.Color.YELLOW
        ),
        Note(
            UUID.randomUUID().toString(),
            "Note 3 title", "Note 3 text",
            Note.Color.GREEN
        ),
        Note(
            UUID.randomUUID().toString(),
            "Note 4 title", "Note 4 text",
            Note.Color.BLUE
        ),
        Note(
            UUID.randomUUID().toString(),
            "Note 4 title", "Note 4 text",
            Note.Color.RED
        ),
        Note(
            UUID.randomUUID().toString(),
            "Note 5 title", "Note 5 text",
            Note.Color.VIOLET
        ),
        Note(
            UUID.randomUUID().toString(),
            "Note 6 title", "Note 6 text",
            Note.Color.PINK
        )
    )

    init {
        notesLiveData.value = notes
    }

    fun saveNote(note: Note){
        addOrReplace(note)
        notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note){
        for(i in notes.indices){
            if(notes[i] == note){
                notes[i] = note
                return
            }
        }
        notes.add(note)
    }

    fun getNotes(): LiveData<List<Note>> {
        return notesLiveData
    }
}