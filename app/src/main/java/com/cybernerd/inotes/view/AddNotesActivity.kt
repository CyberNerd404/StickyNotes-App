 package com.cybernerd.inotes.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.cybernerd.inotes.R
import com.cybernerd.inotes.database.AppDatabase
import com.cybernerd.inotes.database.NoteEntity
import com.cybernerd.inotes.utils.showToast
import io.realm.Realm
import kotlinx.coroutines.launch

 class AddNotesActivity : BaseActivity() {

     private lateinit var title:EditText
     private lateinit var description:EditText
     private lateinit var saveNotesBtn:Button
     private lateinit var realm: Realm

     lateinit var mydatabase: AppDatabase
     var noteIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        mydatabase = AppDatabase.invoke(this)

        realm = Realm.getDefaultInstance()
        title = findViewById(R.id.titleEditText)
        description = findViewById(R.id.descriptionEditText)
        saveNotesBtn = findViewById(R.id.saveNotesBtn)

        saveNotesBtn.setOnClickListener {

            AddNotes()
        }

    }

     private fun AddNotes() {
         launch {

             val data = mydatabase.noteDao().getNotes()


             val notes = NoteEntity()
             notes.title = title.text.toString()
             notes.description = description.text.toString()
             notes.id = data.size + 1

             mydatabase.noteDao().insertNote(notes)
             showToast(this@AddNotesActivity, "Notes Added Successfully")
             val intent = Intent(this@AddNotesActivity, MainActivity::class.java)
             startActivity(intent)
             finish()
         }
     }

 }
