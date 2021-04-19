package com.cybernerd.inotes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cybernerd.inotes.database.NoteEntity
import com.cybernerd.inotes.utils.clickListeners
import kotlinx.android.synthetic.main.notes_rv_layout.view.*

class NotesAdapter (private val context: Context, val clickListeners: clickListeners)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var list: List<NoteEntity> = arrayListOf()

    fun setNotes(list: List<NoteEntity>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.notes_rv_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.titleTextView.text = list[position]!!.title
        holder.itemView.descriptionTextView.text = list[position]!!.description

        holder.itemView.setOnClickListener {
            clickListeners.noteClickListener(list[position])
        }


    }


    class ViewHolder(v:View?): RecyclerView.ViewHolder(v!!){

        val title = itemView.findViewById<TextView>(R.id.titleTextView)
        val description = itemView.findViewById<TextView>(R.id.descriptionTextView)
        val cardView = itemView.findViewById<CardView>(R.id.cardNotes)

    }

}