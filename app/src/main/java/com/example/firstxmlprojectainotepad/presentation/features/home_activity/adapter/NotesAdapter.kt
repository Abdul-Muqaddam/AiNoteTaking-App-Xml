package com.example.firstxmlprojectainotepad.presentation.features.home_activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstxmlprojectainotepad.data.entities.NoteWithImages
import com.example.firstxmlprojectainotepad.databinding.ItemNoteScreenBinding

class NotesAdapter(
    private var notesList: List<NoteWithImages>
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    override fun getItemCount(): Int = notesList.size

    fun updateData(newNotes: List<NoteWithImages>) {
        this.notesList = newNotes
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(private val binding: ItemNoteScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteWithImages: NoteWithImages) {
            binding.noteTitle.text = noteWithImages.note.title
            binding.noteDescription.text = noteWithImages.note.description
            binding.noteDate.text = noteWithImages.note.date
        }
    }
}
