package Database

import Model.Note
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DAO {

    @Query("SELECT * FROM nots_list")
    fun getAllNote(): LiveData<List<Note>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note):Long

    @Delete
    suspend fun delete(note: List<Note>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note:Note)

    @Query("DELETE FROM nots_list WHERE id =:ids")
    suspend fun deleteByID(ids:Int)

}