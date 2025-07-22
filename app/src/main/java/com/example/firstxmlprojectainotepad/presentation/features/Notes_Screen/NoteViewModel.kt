package com.example.firstxmlprojectainotepad.presentation.features.Notes_Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstxmlprojectainotepad.data.entities.NoteEntity
import com.example.firstxmlprojectainotepad.data.entities.NoteWithImages
import com.example.firstxmlprojectainotepad.domain.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val repository: NoteRepository
) : ViewModel() {

    private val _notes = MutableStateFlow<List<NoteWithImages>>(emptyList())
    val notes: StateFlow<List<NoteWithImages>> = _notes

    fun loadAllNotes() {
        viewModelScope.launch {
            repository.getAllNotesWithImages().collect {
                _notes.value = it
            }
        }
    }

    fun getNoteById(id: Long, onResult: (NoteWithImages?) -> Unit) {
        viewModelScope.launch {
            val result = repository.getNoteById(id)
            onResult(result)
        }
    }
    fun saveNote(title: String, description: String, date: String) {
        viewModelScope.launch {
            val noteEntity= NoteEntity(title=title,description=description,date=date)
            repository.insertNote(note =noteEntity)
        }
    }
}