package com.ajayam.roomcrud.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ajayam.roomcrud.dao.NotesKDao
import com.ajayam.roomcrud.model.NotesApp
import com.ajayam.roomcrud.model.NotesK
import com.ajayam.roomcrud.repository.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesKViewModel(application: Application) : AndroidViewModel(application) {

    val retriveNotesK: LiveData<List<NotesK>>
    private val repository: NotesRepository
    private val notesKDao : NotesKDao

    init {
        notesKDao = (application as NotesApp).db.notesKDao()
        repository = NotesRepository(notesKDao)
        retriveNotesK = repository.retriveNotesK
    }

    fun addNote(noteK: NotesK) = CoroutineScope(Dispatchers.IO).launch {
        repository.insert(noteK)
    }

    fun updateNote(noteK:NotesK) = CoroutineScope(Dispatchers.IO).launch {
        repository.update(noteK)
    }

    fun deleteNote(noteK: NotesK) = CoroutineScope(Dispatchers.IO).launch {
        repository.delete(noteK)
    }
}