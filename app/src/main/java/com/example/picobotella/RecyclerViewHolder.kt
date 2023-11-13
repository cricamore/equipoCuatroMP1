package com.example.picobotella

import androidx.recyclerview.widget.RecyclerView
import com.example.picobotella.databinding.RetoLayoutBinding
import com.example.picobotella.model.Reto

class RecyclerViewHolder(binding: RetoLayoutBinding): RecyclerView.ViewHolder(binding.root) {
    val bindingItem = binding

    fun setItemReto(reto: Reto){
        bindingItem.contenido.text = reto.reto
    }
}