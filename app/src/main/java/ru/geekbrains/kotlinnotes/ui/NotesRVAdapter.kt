package ru.geekbrains.kotlinnotes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import ru.geekbrains.kotlinnotes.R
import ru.geekbrains.kotlinnotes.data.entity.Note

class NotesRVAdapter : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])


    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(note: Note) = with(itemView){
            in_title.text = note.title
            in_body.text = note.body
            setBackgroundColor(note.color)
        }
    }

}