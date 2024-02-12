package com.example.mytodo.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytodo.R
import com.example.mytodo.databinding.FragmentUpdateBinding
import com.example.mytodo.model.Note
import com.example.mytodo.viewmodel.NoteViewModel

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.etNoteHeader.setText(args.currentNote.noteHead)
        binding.etNoteBody.setText(args.currentNote.noteBody)


        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_items, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_add -> {
                        updateNote()
                        true
                    }

                    R.id.action_delete -> {
                        deleteNote()
                        true
                    }

                    else -> false
                }

            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    private fun updateNote() {

        val noteHead = binding.etNoteHeader.text.toString()
        val noteBody = binding.etNoteBody.text.toString()

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        val note = Note(args.currentNote.id, noteHead, noteBody)
        viewModel.updateNote(note)

        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    private fun deleteNote() {

        val deletedNote =
            Note(args.currentNote.id, args.currentNote.noteHead, args.currentNote.noteBody)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        viewModel.deleteNote(deletedNote)

        findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        Toast.makeText(requireActivity(), "Note Deleted", Toast.LENGTH_SHORT).show()

    }


}