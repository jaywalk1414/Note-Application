package com.darksuit.owl.mynote.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.darksuit.owl.mynote.R
import com.darksuit.owl.mynote.Ui.HomeFragmentDirections
import com.darksuit.owl.mynote.db.Note
import kotlinx.android.synthetic.main.item_adapter.view.*

class RecyclerAdapter(val listNote : List<Note>) : RecyclerView.Adapter<RecyclerAdapter.NoteViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).
                inflate(R.layout.item_adapter, parent, false))
    }

    override fun getItemCount()= listNote.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.itemView.card_title_recycler.text = listNote[position].title
        holder.itemView.card_note_recycler.text = listNote[position].note

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToNote()
            action.note = listNote[position]
            Navigation.findNavController(it).navigate(action)
        }
    }

    class NoteViewHolder(view : View) : RecyclerView.ViewHolder(view)

}