package ru.geekbrains.kotlinnotes.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_note_edit.*
import ru.geekbrains.kotlinnotes.R
import ru.geekbrains.kotlinnotes.data.entity.Note
import ru.geekbrains.kotlinnotes.ui.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.*

class NoteEditActivity : BaseActivity<Note?, NoteEditViewState>() {
    companion object {
        private val EXTRA_NOTE = NoteEditActivity::class.java.name + "extra.NOTE"
        private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, noteId: String? = null) {
            val intent = Intent(context, NoteEditActivity::class.java)
            intent.putExtra(EXTRA_NOTE, noteId)
            context.startActivity(intent)
        }
    }

    override val layoutRes = R.layout.activity_note_edit
    override val viewModel: NoteEditViewModel by lazy { ViewModelProvider(this).get(NoteEditViewModel::class.java) }
    private var note: Note? = null

    val textChahgeListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            saveNote()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val noteId = intent.getStringExtra(EXTRA_NOTE)

        noteId?.let {
            viewModel.loadNote(it)
        } ?: let {
            supportActionBar?.title = getString(R.string.defaultNoteTitle)
        }
    }

    override fun renderData(data: Note?) {
        this.note = data
        supportActionBar?.title = this.note?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(note!!.lastChanged)
        } ?: getString(R.string.defaultNoteTitle)

        initView()
    }

    fun initView() {
        note?.let { note ->
            ane_title.setText(note.title)
            ane_body.setText(note.body)
            val color = when (note.color) {
                Note.Color.WHITE -> R.color.white
                Note.Color.YELLOW -> R.color.yellow
                Note.Color.GREEN -> R.color.green
                Note.Color.BLUE -> R.color.blue
                Note.Color.RED -> R.color.red
                Note.Color.VIOLET -> R.color.violet
                Note.Color.PINK -> R.color.pink
            }

            toolbar.setBackgroundColor(ContextCompat.getColor(this, color))
        }

        ane_title.addTextChangedListener(textChahgeListener)
        ane_body.addTextChangedListener(textChahgeListener)
    }

    fun saveNote() {
        if (ane_title.text == null || ane_title.text!!.length < 3) return

        note = note?.copy(
            title = ane_title.text.toString(),
            body = ane_body.text.toString(),
            lastChanged = Date()
        ) ?: Note(
            UUID.randomUUID().toString(),
            ane_title.text.toString(),
            ane_body.text.toString()
        )

        note?.let { viewModel.save(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed();
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
