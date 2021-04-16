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
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.notes_rv_layout.view.*

class NotesAdapter (private val context: Context, private val notesList : List<NoteEntity>, val clickListeners: clickListeners)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.notes_rv_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.titleTextView.text = notesList[position]!!.title
        holder.itemView.descriptionTextView.text = notesList[position]!!.description
        holder.itemView.idTextView.text = notesList[position]!!.id.toString()

        holder.itemView.setOnClickListener {
            clickListeners.noteClickListener(notesList[position])
        }

    }


    class ViewHolder(v:View?): RecyclerView.ViewHolder(v!!){

        val title = itemView.findViewById<TextView>(R.id.titleTextView)
        val description = itemView.findViewById<TextView>(R.id.descriptionTextView)
        val id = itemView.findViewById<TextView>(R.id.idTextView)
        val cardView = itemView.findViewById<CardView>(R.id.cardNotes)

    }

}