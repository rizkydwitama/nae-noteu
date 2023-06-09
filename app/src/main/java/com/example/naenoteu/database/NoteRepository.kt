package com.example.naenoteu.database

import androidx.lifecycle.LiveData
import com.example.naenoteu.model.Note

class NoteRepository(val noteDao: NoteDao?) {
    val allNote : LiveData<List<Note>> = noteDao!!.getAllNotes()

    suspend fun insert(note : Note){
        noteDao!!.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao!!.delete(note)
    }

    suspend fun update(note: Note){
        noteDao!!.update(note.id, note.title, note.note)
    }
}