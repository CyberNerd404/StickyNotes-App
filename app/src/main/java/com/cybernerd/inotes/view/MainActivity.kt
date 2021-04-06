package com.cybernerd.inotes.view

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cybernerd.inotes.NotesAdapter
import com.cybernerd.inotes.R
import com.cybernerd.inotes.database.AppDatabase
import com.cybernerd.inotes.database.NoteEntity
import com.cybernerd.inotes.utils.clickListeners
import com.cybernerd.inotes.utils.showToast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import kotlinx.coroutines.launch

class MainActivity : BaseActivity(), clickListeners {

    private lateinit var addNotes : FloatingActionButton
    private lateinit var notesRV : RecyclerView

    lateinit var mydatabase: AppDatabase
    lateinit var noteList: List<NoteEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNotes = findViewById(R.id.addNotes)
        notesRV = findViewById(R.id.notesRV)

        mydatabase = AppDatabase.invoke(this)
        noteList = arrayListOf()

        addNotes.setOnClickListener {
            startActivity(Intent(this, AddNotesActivity::class.java))
            finish()
        }


        notesRV.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)

        getAllNotes()



    }

    private fun getAllNotes() {

        launch {
            val data = mydatabase.noteDao().getNotes()
            notesRV.adapter = NotesAdapter(this@MainActivity, data, this@MainActivity)
            notesRV.adapter!!.notifyDataSetChanged()
        }


    }

    override fun noteClickListener(note: NoteEntity) {
        showToast(this, "Note : ${note.id}")
    }
}
