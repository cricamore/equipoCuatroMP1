package com.example.picobotella

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picobotella.databinding.RetoLayoutBinding
import com.example.picobotella.model.Reto

class RecyclerAdapter(private val listaRetos: MutableList<Reto>): RecyclerView.Adapter<RecyclerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = RetoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listaRetos.size
    }

    override fun onBindViewHolder(recyclerViewHolder:RecyclerViewHolder, position: Int) {
        val reto = listaRetos[position]
        recyclerViewHolder.setItemReto(reto)
    }
}