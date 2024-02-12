package com.example.mytodo.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mytodo.R
import com.example.mytodo.data.NoteAdapter
import com.example.mytodo.databinding.FragmentListBinding
import com.example.mytodo.viewmodel.NoteViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)


        val adapter = NoteAdapter()

        viewModel = ViewModelProvider(requireActivity())[NoteViewModel::class.java]

        viewModel.readAllData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }


        binding.recycler.adapter = adapter




        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }



        return binding.root
    }


}