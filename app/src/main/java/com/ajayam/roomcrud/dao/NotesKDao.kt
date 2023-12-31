package com.ajayam.roomcrud.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ajayam.roomcrud.model.NotesK

@Dao
interface NotesKDao {
    @Insert
    suspend fun insert(note: NotesK)

    @Update
    suspend fun update(note: NotesK)

    @Delete
    suspend fun delete(note: NotesK)

    @Query("Select * from `notes-kotlin`")
    fun retrieveAllNotes(): LiveData<List<NotesK>>
}