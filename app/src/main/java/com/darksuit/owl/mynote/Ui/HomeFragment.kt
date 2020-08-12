package com.darksuit.owl.mynote.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.darksuit.owl.mynote.R
import com.darksuit.owl.mynote.db.NoteDataBase
import com.darksuit.owl.mynote.widget.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_note.setHasFixedSize(true)
        recycler_note.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                val notes = NoteDataBase(it).getNoteDao().getAllNotes()
                recycler_note.adapter = RecyclerAdapter(notes)
            }
        }

        button_add.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToNote()
            Navigation.findNavController(it).navigate(action)
        }
    }

}