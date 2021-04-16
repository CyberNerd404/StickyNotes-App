package com.cybernerd.inotes.database

import androidx.room.Entity

@Entity(tableName = "note_table")
data class NoteEntity(
    @androidx.room.PrimaryKey
    var id: Int? = null,
    var title:String? = null,
    var description:String? = null
)