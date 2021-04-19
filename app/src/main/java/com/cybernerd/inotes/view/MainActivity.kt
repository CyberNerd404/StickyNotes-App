package com.cybernerd.inotes.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cybernerd.inotes.NotesAdapter
import com.cybernerd.inotes.R
import com.cybernerd.inotes.database.AppDatabase
import com.cybernerd.inotes.database.NoteEntity
import com.cybernerd.inotes.utils.clickListeners
import com.cybernerd.inotes.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), clickListeners {


    lateinit var mydatabase: AppDatabase
    lateinit var noteList: List<NoteEntity>
    lateinit var notesAdapter: NotesAdapter

    private val viewModel: MainActivityViewModel by lazy {
        MainActivityViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // full screen
        /*requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)*/

        setContentView(R.layout.activity_main)


        notesAdapter = NotesAdapter(this, this)
        mydatabase = AppDatabase.invoke(this)
        noteList = arrayListOf()


        notesRV.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        notesRV.adapter = notesAdapter

        addNotes.setOnClickListener {
            startActivity(Intent(this, AddNotesActivity::class.java))
            finish()
        }

        getAllNotes()


    }

    private fun getAllNotes() {
        viewModel.noteData.observe(this, Observer {
            notesAdapter.setNotes(it)
        })
    }

    override fun noteClickListener(note: NoteEntity) {
        Intent(this, AddNotesActivity::class.java).apply {
            putExtra("id", note.id)
            this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(this)

        }
    }

}
