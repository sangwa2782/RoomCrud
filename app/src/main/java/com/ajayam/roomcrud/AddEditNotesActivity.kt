package com.ajayam.roomcrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ajayam.roomcrud.databinding.ActivityAddEditNotesBinding
import com.ajayam.roomcrud.model.NotesK
import com.ajayam.roomcrud.viewModel.NotesKViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddEditNotesActivity : AppCompatActivity() {
    private var binding: ActivityAddEditNotesBinding?= null
    private lateinit var notesViewModel: NotesKViewModel

    private var noteType: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNotesBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //! Action Bar title: *****************************
        supportActionBar?.title = ""

        //! View Model ***********************************
        notesViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NotesKViewModel::class.java]

        //! getting note type -> New or Edit *************
        noteType = intent.getStringExtra("noteType")

        //! Setting view according to note type ***********
        if (noteType!! == "Edit") {
            binding?.btnSaveNote?.text = "Update"
            binding?.etNoteTitle?.setText(intent.getStringExtra("noteTitle"))
            binding?.etNoteContent?.setText(intent.getStringExtra("noteContent"))
        } else {
            binding?.btnSaveNote?.text = "Save"
        }


        //! save button
        binding?.btnSaveNote?.setOnClickListener {
            if (noteType!! == "New") {
                saveNote()
            } else {
                updateNote()
            }
        }
    }

    //! update Note
    private fun updateNote() {
        val noteTitle = binding?.etNoteTitle?.text?.toString()
        val noteContent = binding?.etNoteContent?.text?.toString()
        if (noteTitle!!.isNotEmpty() && noteContent!!.isNotEmpty()) {
            val sdf = SimpleDateFormat("dd MMM, yyyy-hh:mm a", Locale.US)
            val currentDateAndTime = sdf.format(Date())
            val updatedNote = NotesK(id = intent.getIntExtra("noteId", -1), noteTitle = noteTitle, noteContent =  noteContent, noteTimeStamp =  currentDateAndTime)
            notesViewModel.updateNote(updatedNote)
            finish()
        } else {
            Toast.makeText(this, "Enter all fields!", Toast.LENGTH_SHORT).show()
        }
    }


    //! Save note
    private fun saveNote() {
        val noteTitle = binding?.etNoteTitle?.text?.toString()
        val noteContent = binding?.etNoteContent?.text?.toString()
        if (noteTitle!!.isNotEmpty() && noteContent!!.isNotEmpty()) {
            val sdf = SimpleDateFormat("dd MMM, yyyy-hh:mm a", Locale.US)
            val currentDateAndTime = sdf.format(Date())
            notesViewModel.addNote (
                NotesK(
                    noteTitle = noteTitle,
                    noteContent = noteContent,
                    noteTimeStamp = currentDateAndTime
                )
            )
            finish()
        } else {
            Toast.makeText(this, "Enter all fields!", Toast.LENGTH_SHORT).show()
        }
    }

    //! on back pressed
    override fun onBackPressed() {
        super.onBackPressed()
        if (binding?.etNoteTitle?.text!!.isNotEmpty() && binding?.etNoteContent?.text!!.isNotEmpty()) {
            if (noteType!! == "New") {
                saveNote()
            } else {
                updateNote()
            }
        } else {
            finish()
        }
    }

    //! on Destroy
    override fun onDestroy() {
        super.onDestroy()
        if (binding != null) {
            binding = null
        }
    }
}