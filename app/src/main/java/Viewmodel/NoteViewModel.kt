package Viewmodel

import Model.Note
import Repository.NoteRepo
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val liveNoteList:LiveData<List<Note>>
    private lateinit var repo:NoteRepo

    init {
        repo=NoteRepo(application.applicationContext)
        liveNoteList= repo.getAllNote()
    }

     fun LiveNots():LiveData<List<Note>>{
        return liveNoteList
    }

    fun insertNote(note: Note){

        CoroutineScope(Dispatchers.IO).launch {
        repo.insertNote(note)
        }
    }
    fun updateNote(note: Note){

        CoroutineScope(Dispatchers.IO).launch {
            repo.updateNote(note)
        }

    }
    fun deleteNots(notes: MutableList<Int>){
        CoroutineScope(Dispatchers.IO).launch {
            notes.forEach {
             repo.deleteNotes(it)
            }

        }
    }
}



