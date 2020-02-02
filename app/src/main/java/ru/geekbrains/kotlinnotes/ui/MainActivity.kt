package ru.geekbrains.kotlinnotes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import ru.geekbrains.kotlinnotes.R
import ru.geekbrains.kotlinnotes.data.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        rv_notes.layoutManager

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.viewState().observe(this, Observer {
            it?.let {
                adapter.notes = it.notes
            }
        })

    }
}
