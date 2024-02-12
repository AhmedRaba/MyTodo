package com.example.mytodo.data

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.R
import com.example.mytodo.databinding.NoteItemBinding
import com.example.mytodo.frags.ListFragmentDirections
import com.example.mytodo.model.Note
import kotlin.random.Random

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var noteList = listOf<Note>()

    inner class NoteViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val currentItem = noteList[position]

        holder.binding.noteHeader.text = currentItem.noteHead
        holder.binding.noteBody.text = currentItem.noteBody
        holder.binding.cardView.setCardBackgroundColor(getRandomColor())


        //save checkbox state

        val sharedPreferences =
            holder.itemView.context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val identifier = "checkbox_state_$position"

        holder.binding.checkBox.isChecked =
            sharedPreferences.getBoolean(identifier, false)

        holder.binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            editor.putBoolean(identifier, isChecked)
            editor.apply()
        }

        //navigate to update fragment
        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem))
        }


    }

    private fun getRandomColor(): Int {
        val random = Random
        return when (random.nextInt(4)) {
            0 -> Color.argb(255, 33, 150, 243) // Light Blue
            1 -> Color.argb(255, 255, 235, 0) // Yellow
            2 -> Color.argb(255, 255, 99, 71) // Orange
            else -> Color.argb(255, 0, 128, 0) // Green
        }

    }


    fun setData(note: List<Note>) {
        this.noteList = note
        notifyDataSetChanged()
    }

}