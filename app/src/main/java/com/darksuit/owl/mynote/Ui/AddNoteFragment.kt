package com.darksuit.owl.mynote.Ui

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.darksuit.owl.mynote.R
import com.darksuit.owl.mynote.db.Note
import com.darksuit.owl.mynote.db.NoteDataBase
import com.darksuit.owl.mynote.widget.toast
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch


class AddNoteFragment() : BaseFragment() {

    private var note : Note? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            note = AddNoteFragmentArgs.fromBundle(it).note
            note_title.setText(note?.title)
            note_description.setText(note?.note)
        }


        button_done_note.setOnClickListener {view->

            val titleString : String = note_title.text.toString().trim()
            val noteString : String = note_description.text.toString().trim()

            if (titleString.isEmpty()){
                note_title.error = "Title Required"
                note_title.requestFocus()
                return@setOnClickListener
            }

            if (noteString.isEmpty()){
                note_description.error = "note required"
                note_description.requestFocus()
                return@setOnClickListener
            }

            launch {
                context?.let {
                    val mNote = Note(titleString, noteString)

                    if (note != null){
                        mNote.id = note!!.id
                        NoteDataBase(it).getNoteDao().updateNote(mNote)
                        it.toast("updated")
                    }
                    else{
                        NoteDataBase(it).getNoteDao().addNote(mNote)
                        it.toast("success")
                    }

                    val action = AddNoteFragmentDirections.actionNoteToHome()
                    Navigation.findNavController(view).navigate(action)
                    }
            }


        }
    }

}