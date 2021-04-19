package com.cybernerd.inotes.database

import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(
        note: NoteEntity
    )

    @Query("SELECT * FROM note_table")
    suspend fun getNotes(): List<NoteEntity>

    @Query("SELECT * FROM note_table WHERE id =:id")
    suspend fun getNote(id: Int): NoteEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: NoteEntity)

    @Query("DELETE FROM note_table WHERE id =:id")
    suspend fun deleteNote(id: Int)
}