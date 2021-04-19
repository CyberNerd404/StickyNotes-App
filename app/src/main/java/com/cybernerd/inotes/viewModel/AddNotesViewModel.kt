package com.cybernerd.inotes.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cybernerd.inotes.database.AppDatabase
import com.cybernerd.inotes.database.NoteEntity
import com.cybernerd.inotes.utils.CallbackListener
import com.cybernerd.inotes.utils.debug
import kotlinx.coroutines.launch

class AddNotesViewModel(context: Context): ViewModel() {

    var mydatabase = AppDatabase(context)


    fun addNoteDB(title: String, description: String, callbackListener : CallbackListener){
        viewModelScope.launch {
            val data = mydatabase.noteDao().getNotes()
            val notes = NoteEntity()
            notes.title = title
            notes.description = description
            notes.id = data.size + 1
            mydatabase.noteDao().insertNote(notes)
            debug("add note", "success")
            callbackListener.callback(true)
        }
    }

    fun editNoteDB(id: Int,title: String, description: String, callbackListener: CallbackListener){
        viewModelScope.launch {
            val notes = NoteEntity()
            notes.id = id
            notes.title = title
            notes.description = description
            mydatabase.noteDao().updateNote(notes)
            debug("add note", "success")
            callbackListener.callback(true)
        }
    }

    fun deleteNodeDB(id: Int, callbackListener: CallbackListener){
        viewModelScope.launch {
            mydatabase.noteDao().deleteNote(id)
            callbackListener.callback(true)
        }
    }
}