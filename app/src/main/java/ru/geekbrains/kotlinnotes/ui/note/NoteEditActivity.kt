package ru.geekbrains.kotlinnotes.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_note_edit.*
import org.jetbrains.anko.alert
import org.koin.android.viewmodel.ext.android.viewModel
import ru.geekbrains.kotlinnotes.R
import ru.geekbrains.kotlinnotes.common.getColorInt
import ru.geekbrains.kotlinnotes.data.entity.Note
import ru.geekbrains.kotlinnotes.ui.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.*

class NoteEditActivity : BaseActivity<NoteEditViewState.Data, NoteEditViewState>() {
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
    override val model: NoteEditViewModel  by viewModel()
    private var note: Note? = null
    var color = Note.Color.WHITE

    private val textChangeListener = object : TextWatcher {
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
            model.loadNote(it)
        } ?: let {
            supportActionBar?.title = getString(R.string.defaultNoteTitle)
            initView()
        }
    }

    override fun renderData(data: NoteEditViewState.Data) {
        if (data.isDeleted) finish()
        this.note = data.note
        initView()
    }

    private fun initView() {
        note?.let { note ->
            removeEditListener()
            ane_title.setText(note.title)
            ane_body.setText(note.body)
            toolbar.setBackgroundColor(note.color.getColorInt(this))
            supportActionBar?.title = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(note.lastChanged)
        } ?: let {
            supportActionBar?.title =   getString(R.string.new_note_title)
        }

        setEditListener()

        colorPicker.onColorClickListener = {
            toolbar.setBackgroundColor(color.getColorInt(this))
            color = it
            saveNote()
        }
    }

    private fun removeEditListener(){
        ane_title.removeTextChangedListener(textChangeListener)
        ane_body.removeTextChangedListener(textChangeListener)
    }

    private fun setEditListener(){
        ane_title.removeTextChangedListener(textChangeListener)
        ane_body.removeTextChangedListener(textChangeListener)
    }

    fun saveNote() {
        if (ane_title.text == null || ane_title.text!!.length < 3) return

        note = note?.copy(
            title = ane_title.text.toString(),
            body = ane_body.text.toString(),
            lastChanged = Date(),
            color = color
        ) ?: Note(
            UUID.randomUUID().toString(),
            ane_title.text.toString(),
            ane_body.text.toString(),
            color
        )

        note?.let { model.save(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu?) = menuInflater.inflate(R.menu.note_edit, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> onBackPressed().let { true }
        R.id.palette -> togglePalette().let { true }
        R.id.delete -> deleteNote().let { true }
        else -> super.onOptionsItemSelected(item)
    }

    private fun togglePalette() {
        if (colorPicker.isOpen) {
            colorPicker.close()
        } else {
            colorPicker.open()
        }
    }

    private fun deleteNote() {
        alert {
            messageResource = R.string.note_delete_message
            negativeButton(R.string.note_delete_cancel) { dialog -> dialog.dismiss() }
            positiveButton(R.string.note_delete_ok) { model.deleteNote() }
        }.show()
    }
}
