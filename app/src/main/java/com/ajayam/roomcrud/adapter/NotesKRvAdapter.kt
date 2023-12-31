package com.ajayam.roomcrud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ajayam.roomcrud.R
import com.ajayam.roomcrud.model.NotesK

class NotesKRvAdapter(
    private val allNotesList: ArrayList<NotesK>,
    private val noteClickListener: (noteK: NotesK) -> Unit,
    private val deleteNoteListener: (noteK: NotesK) -> Unit
) : RecyclerView.Adapter<NotesKRvAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.note_list_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return allNotesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvRvTitle.text = allNotesList[position].noteTitle
        holder.tvRvTimeStamp.text = "Last Updated: ${allNotesList[position].noteTimeStamp}"
        holder.itemView.setOnClickListener{
            noteClickListener.invoke(allNotesList[position])
        }
        holder.ivRvDeleteButton.setOnClickListener{
            deleteNoteListener.invoke(allNotesList[position])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRvTitle: TextView
        val tvRvTimeStamp: TextView
        val ivRvDeleteButton: ImageView
        init {
            tvRvTitle = itemView.findViewById(R.id.tvRvTitle)
            tvRvTimeStamp = itemView.findViewById(R.id.tvRvTimeStamp)
            ivRvDeleteButton = itemView.findViewById(R.id.ivRvDeleteButton)
        }
    }

}