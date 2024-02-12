package com.example.mytodo.frags

import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mytodo.R
import com.example.mytodo.databinding.FragmentAddBinding
import com.example.mytodo.model.Note
import com.example.mytodo.viewmodel.NoteViewModel

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)


        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_items, menu)
                menu.findItem(R.id.action_delete).setVisible(false)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_add -> {
                        addNotes()
                        true
                    }
                    else -> false
                }

            }
        },viewLifecycleOwner,Lifecycle.State.RESUMED)



        return binding.root
    }


    fun addNotes() {
        viewModel = ViewModelProvider(requireActivity())[NoteViewModel::class.java]

        val noteHeader = binding.etNoteHeader.text.toString()
        val noteBody = binding.etNoteBody.text.toString()

        val note = Note(0, noteHeader, noteBody)

        viewModel.addNote(note)

        findNavController().navigate(R.id.action_addFragment_to_listFragment)

    }


}