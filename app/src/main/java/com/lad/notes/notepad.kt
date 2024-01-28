package com.lad.notes

import Model.Note
import Viewmodel.NoteViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_notepad.*
import java.text.SimpleDateFormat
import java.util.Date

class notepad : AppCompatActivity() {
    var isNew:Boolean=true
    var viewModel:NoteViewModel?=null
    var myBundle:Bundle? = null
    private val dateFormate=SimpleDateFormat("EEE, dd MMM ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notepad)

       viewModel=ViewModelProvider(this).get(NoteViewModel::class.java)

       myBundle= intent.extras?.getBundle("myBundle")

        if(myBundle!=null){
            isNew=false
            title_pad.setText(myBundle!!.getString("title").toString())
            descripition_pad.setText(myBundle!!.getString("descripition").toString())
            val date=Date(myBundle!!.getLong("time"))
            date_pad.setText(dateFormate.format(date).toString())
        }
        else{
            date_pad.setText(dateFormate.format(Date().time).toString())
        }

    }
    override fun onStop() {
        super.onStop()

        if(isNew && title_pad.text.toString().trim() !=""){
            Toast.makeText(this, "Note created", Toast.LENGTH_SHORT).show()
            viewModel?.insertNote(Note(title_pad.text.toString(),descripition_pad.text.toString()
                ,Date().time))
        }
        else if(myBundle!=null){

                val id:Int?= myBundle!!.getInt("id",-1)
                if(id!=-1){

               viewModel!!.updateNote(Note(id,title_pad.text.toString().trim(),descripition_pad.text.toString().trim(),Date().time))
                }
            }


    }
}