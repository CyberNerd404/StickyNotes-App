package com.cybernerd.inotes.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.cybernerd.inotes.database.AppDatabase
import com.cybernerd.inotes.database.NoteEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivityViewModel(context: Context): ViewModel() {
    var noteData: MutableLiveData<List<NoteEntity>> = MutableLiveData()
    var mydatabase = AppDatabase(context)
    init {
        viewModelScope.launch {
            noteData.value = getNotes()
        }
    }

    private suspend fun getNotes(): List<NoteEntity> {

        return withContext(Dispatchers.IO) {
                mydatabase.noteDao().getNotes()
            }

    }



}
