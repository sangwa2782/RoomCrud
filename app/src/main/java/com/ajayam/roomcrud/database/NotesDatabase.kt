package com.ajayam.roomcrud.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ajayam.roomcrud.dao.NotesKDao
import com.ajayam.roomcrud.model.NotesK

@Database(entities = [NotesK::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesKDao(): NotesKDao

    companion object {
        @Volatile
        private var INSTANCE : NotesDatabase? = null

        fun getDatabase(context: Context) : NotesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
