package Repository

import Database.DAO
import Database.NoteDatabase
import Model.Note
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData

open class NoteRepo(var context:Context){

    val Dao:DAO=NoteDatabase.getDatabse(context).getDao()

    fun getAllNote():LiveData<List<Note>> {
        return Dao.getAllNote()
    }
    suspend fun insertNote(note:Note){
        Dao.insertNote(note)
    }
    suspend fun deleteNotes(id:Int){
        Dao.deleteByID(id)
    }
    suspend fun updateNote(note: Note){
        Dao.updateNote(note)
    }
}