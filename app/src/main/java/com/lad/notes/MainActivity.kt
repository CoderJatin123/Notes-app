package com.lad.notes

import Recyclerview.RecyclerViewAdapter
import Recyclerview.onDelete
import Viewmodel.NoteViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.note.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class MainActivity : AppCompatActivity(),onDelete {

    private lateinit var viewmodel:NoteViewModel
    private var isInit:Boolean=true
    lateinit var myRecyclerViewAdapter:RecyclerViewAdapter
    lateinit var deleteList:MutableList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel= NoteViewModel(this.application)

        viewmodel.LiveNots().observe(this, Observer { list->

            if(list!=null){
                if(isInit){
                    myRecyclerViewAdapter= RecyclerViewAdapter(this,list,this)
                    recyclerview.layoutManager=LinearLayoutManager(this)
                    recyclerview.adapter=myRecyclerViewAdapter

                }
            }
        })

        delete_icon.setOnClickListener {
            Toast.makeText(this, "Notes Deleted", Toast.LENGTH_SHORT).show()

            if(deleteList.size>0){

                viewmodel.deleteNots(deleteList)
                delete_icon.visibility=View.GONE
            }
        }


        addnote.setOnClickListener {
           startActivity(Intent(this,notepad::class.java))
        }
    }

    override fun onDeleteShow(isShow: Boolean) {
         if(isShow){
             delete_icon.visibility=View.VISIBLE
         }

        else
             delete_icon.visibility=View.INVISIBLE
    }

    override fun onDelete(ids: MutableList<Int>) {
      deleteList=ids
    }

}