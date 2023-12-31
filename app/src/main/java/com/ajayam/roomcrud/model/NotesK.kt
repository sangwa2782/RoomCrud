package com.ajayam.roomcrud.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes-kotlin")
data class NotesK(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val noteTitle: String = "",
    val noteContent: String = "",
    val noteTimeStamp: String = ""
)
