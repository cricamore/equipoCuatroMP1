package com.cristian.miniproyecto1.view

import androidx.recyclerview.widget.RecyclerView
import com.cristian.miniproyecto1.databinding.RetoLayoutBinding
import com.cristian.miniproyecto1.model.Reto

class RecyclerViewHolder(binding: RetoLayoutBinding): RecyclerView.ViewHolder(binding.root) {
    val bindingItem = binding

    fun setItemReto(reto: Reto){
        bindingItem.contenido.text = reto.reto
    }
}