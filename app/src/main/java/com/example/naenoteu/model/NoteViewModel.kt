package com.example.naenoteu.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.naenoteu.database.NoteDatabase
import com.example.naenoteu.database.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : NoteRepository
    val allNote : LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application)?.getNoteDao()
        repository = NoteRepository(dao)
        allNote = repository.allNote
    }

    fun deleteNote(note : Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }

    fun insertNote(note : Note) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }
}