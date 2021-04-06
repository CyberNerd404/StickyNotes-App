package com.cybernerd.inotes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(
        note: NoteEntity
    )

    @Query("SELECT * FROM note_table")
    suspend fun getNotes(): List<NoteEntity>
}