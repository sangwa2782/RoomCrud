package com.ajayam.roomcrud

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajayam.roomcrud.adapter.NotesKRvAdapter
import com.ajayam.roomcrud.databinding.ActivityMainBinding
import com.ajayam.roomcrud.model.NotesK
import com.ajayam.roomcrud.viewModel.NotesKViewModel


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private lateinit var notesKRvAdapter: NotesKRvAdapter
     private lateinit var notesKViewModel: NotesKViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //! setting action bar title
        supportActionBar?.title = "Your Notes"

        // Setting Recycler View:
        binding?.rvNotes?.layoutManager = LinearLayoutManager(this)

        //! setting View Model and creating adapter
//        notesKViewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
//        )[NotesKViewModel::class.java]

//        notesKViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
//            .getInstance(application))[NotesKViewModel::class.java]

        notesKViewModel = ViewModelProvider(this).get(NotesKViewModel::class.java)

        //! retrieving notes
        notesKViewModel.retriveNotesK.observe(this) { list ->
            if (list.isEmpty()) {
                binding?.rvNotes?.visibility = View.GONE
                binding?.tvNoNotes?.visibility = View.VISIBLE
            } else {
                binding?.rvNotes?.visibility = View.VISIBLE
                binding?.tvNoNotes?.visibility = View.GONE
            }
            list.let { notesKList ->
                notesKRvAdapter = NotesKRvAdapter(ArrayList(notesKList),
                { noteK ->
                    onNoteClick(noteK)
                }, { noteK ->
                    showDeleteDialog(noteK)
                })
                binding?.rvNotes?.adapter = notesKRvAdapter
            }
        }

        //! floating Action button
        binding?.floatingActionButton?.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNotesActivity::class.java)
            intent.putExtra("noteType", "New")
            startActivity(intent)
        }
    }

    // on note click
    private fun onNoteClick(note: NotesK) {
        val intent = Intent(this, AddEditNotesActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteContent", note.noteContent)
        intent.putExtra("noteTimeStamp", note.noteTimeStamp)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
    }


    //! on delete button clicked
    private fun showDeleteDialog(note: NotesK) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Delete")
        val noteTitle = note.noteTitle
        builder.setMessage("Are you sure you want to delete: $noteTitle")
        builder.setPositiveButton("yes") { _, _ ->
            notesKViewModel.deleteNote(note)
            Toast.makeText(this@MainActivity, "Note Deleted!!!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    //! on destroy
    override fun onDestroy() {
        super.onDestroy()
        if (binding != null) {
            binding = null
        }
    }
}