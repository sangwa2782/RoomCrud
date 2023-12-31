package com.ajayam.roomcrud.repository

import androidx.lifecycle.LiveData
import com.ajayam.roomcrud.dao.NotesKDao
import com.ajayam.roomcrud.model.NotesK

class NotesRepository(private val notesKDao: NotesKDao) {

    val retriveNotesK: LiveData<List<NotesK>> = notesKDao.retrieveAllNotes()

    suspend fun insert(notesK: NotesK) {
        notesKDao.insert(notesK)
    }

    suspend fun update(notesK: NotesK) {
        notesKDao.update(notesK)
    }

    suspend fun delete(notesK: NotesK) {
        notesKDao.delete(notesK)
    }
}