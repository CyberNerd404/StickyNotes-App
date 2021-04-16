package com.cybernerd.inotes.utils

import com.cybernerd.inotes.database.NoteEntity

interface clickListeners {

    fun noteClickListener(
        note: NoteEntity
    )
}