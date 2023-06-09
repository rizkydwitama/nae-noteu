package com.example.naenoteu.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.naenoteu.MainActivity
import com.example.naenoteu.R
import com.example.naenoteu.model.Note
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class NoteAdapter(private val context: Context, val listener: MainActivity) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val NoteList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        )
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = NoteList[position]
        holder.title.text = currentNote.title
        holder.notes.text = currentNote.note
        holder.date.text = currentNote.date
        holder.title.isSelected = true
        holder.date.isSelected = true

        holder.noteLayout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))
        holder.noteLayout.setOnClickListener{
            listener.onItemClicked(NoteList[holder.adapterPosition])
        }

        holder.noteLayout.setOnLongClickListener{
            listener.onLongItemClicked(NoteList[holder.adapterPosition], holder.noteLayout)
            true
        }

    }

    override fun getItemCount(): Int {
        return NoteList.size
    }

    fun updateList(newList : List<Note>){
        fullList.clear()
        fullList.addAll(newList)

        NoteList.clear()
        NoteList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search : String){
        NoteList.clear()
        for(item in fullList){
            if(item.title?.lowercase()?.contains(search.lowercase()) == true || item.note?.lowercase()?.contains(search.lowercase()) == true){
                NoteList.add(item)
            }
        }

        notifyDataSetChanged()

    }

    fun randomColor() : Int {
        val list = ArrayList<Int>()

        list.add(R.color.note_color_1)
        list.add(R.color.note_color_2)
        list.add(R.color.note_color_3)
        list.add(R.color.note_color_4)
        list.add(R.color.note_color_5)
        list.add(R.color.note_color_6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

    inner class NoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val noteLayout = itemView.findViewById<CardView>(R.id.cardViewListItem)
        val title = itemView.findViewById<TextView>(R.id.title)
        val notes = itemView.findViewById<TextView>(R.id.note)
        val date = itemView.findViewById<TextView>(R.id.date)
    }

    interface NoteClickListener{
        fun onItemClicked(note:Note)
        fun onLongItemClicked(note:Note, cardView:CardView)
    }

}