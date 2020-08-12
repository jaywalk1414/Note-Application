package com.darksuit.owl.mynote.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote (note : Note)

    @Query("SELECT * FROM note")
    suspend fun getAllNotes (): List<Note>

    @Insert
    suspend fun addMultipleNotes (vararg notes : Note)

    @Update
    suspend fun updateNote (note : Note)

}