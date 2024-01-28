package Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "nots_list")
data class Note(val c_title:String,val c_description:String,val c_time:Long){

    constructor(id: Int?, c_title:String, c_description:String, c_time:Long) : this(c_title,c_description,c_time){
        this.id=id
        this.title=c_title
        this.description=c_description
        this.time_stamp=c_time
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?=null
    set(value){
        field=value
    }

    @ColumnInfo(name = "title")
     var title:String = ""
           get()=field
       set(value) {
           field=value
       }

       @ColumnInfo(name = "description")
        var description:String=""
           get()=field
           set(value) {
               field=value
           }
    @ColumnInfo(name = "time_stamp")
        var time_stamp:Long = 0
           get()=field
           set(value) {
               field=value
           }

       init {
           title=c_title
           description=c_description
           time_stamp=c_time
       }
}