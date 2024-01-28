package Recyclerview

import Model.Note
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lad.notes.R
import com.lad.notes.notepad
import kotlinx.android.synthetic.main.note.view.*

class RecyclerViewAdapter(val cotext:Context,val noteList:List<Note>,val ondelete:onDelete) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var isLongPreesd=false
    private var selectedList= mutableListOf<Int>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(cotext).inflate(R.layout.note,parent,false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     holder.title_view.text=noteList[position].title
     holder.des_view.text=noteList[position].description

        if(position%2==0){
        holder.note_card.backgroundTintList = ContextCompat.getColorStateList(cotext ,R.color.card_blue)
        }

        holder.note_card.setOnClickListener {

            if(isLongPreesd){
                if(selectedList.contains(noteList[position].id)){
                    selectedList.remove(noteList[position].id)

                    holder.select.visibility=View.INVISIBLE
                    ondelete.onDelete(selectedList)

                    if(selectedList.size==0){
                        isLongPreesd=false
                        ondelete.onDeleteShow(false)

                    }

                }
                else{
                    ondelete.onDelete(selectedList)
                    holder.select.visibility=View.VISIBLE
                    noteList[position].id?.let { it1 -> selectedList.add(it1) }
                }

            }
            else
            {
                openNote(cotext,position)
            }

        }

        holder.note_card.setOnLongClickListener {

            selectItem(holder,noteList[position],noteList[position].id)
            return@setOnLongClickListener true
        }

    }

    private fun openNote(cotext: Context, position: Int) {

        val bundle = Bundle()
        val intent = Intent(cotext, notepad::class.java)

        bundle.putString("title", noteList[position].title)
        bundle.putString("descripition", noteList[position].description)
        bundle.putLong("time", noteList[position].time_stamp)
        noteList[position].id?.let { it1 -> bundle.putInt("id", it1) }
        intent.putExtra("myBundle", bundle)
        cotext.startActivity(intent)
    }

    private fun selectItem(holder: RecyclerViewAdapter.ViewHolder, note: Note, id: Int?) {

        if(!isLongPreesd){
            ondelete.onDeleteShow(true)
        }

        isLongPreesd=true
        if (id != null) {
            holder.select.visibility=View.VISIBLE
            note.id?.let { selectedList.add(it) }
            ondelete.onDelete(selectedList)
        }

    }

    override fun getItemCount(): Int {
       return noteList.size
    }

   inner class ViewHolder(var itemView: View):RecyclerView.ViewHolder(itemView){

        val title_view=itemView.item_title
        val des_view=itemView.item_desc
        val card=itemView.card
        val select=itemView.select
       val note_card=itemView.note_card

    }

}
interface onDelete{
     fun onDeleteShow(isShow:Boolean)
     fun onDelete(ids:MutableList<Int>)
}