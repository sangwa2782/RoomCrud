package com.ajayam.roomcrud.model

import android.app.Application
import com.ajayam.roomcrud.database.NotesDatabase

class NotesApp: Application() {
    val db by lazy {
        NotesDatabase.getDatabase(this)
    }
}