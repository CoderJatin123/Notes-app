package Database

import Model.Note
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class],exportSchema = false, version = 1)

abstract class NoteDatabase(): RoomDatabase() {

    abstract fun getDao(): DAO

    companion object{
        @Volatile
        private var INSTANCE :NoteDatabase?=null

        fun getDatabse(context:Context):NoteDatabase{
            val tempInstance= INSTANCE
            if(tempInstance!=null) {
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(context.applicationContext,NoteDatabase::class.java,"myDB")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE=instance
                return instance
            }

        }
    }
}