package com.cristian.miniproyecto1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cristian.miniproyecto1.R
import com.cristian.miniproyecto1.databinding.RetoLayoutBinding
import com.cristian.miniproyecto1.model.Reto


class RecyclerAdapter(private val listaRetos: MutableList<Reto>, private val navController: NavController): RecyclerView.Adapter<RecyclerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerViewHolder {
        val context = parent.context
        println("Adapter Context: $context")
        val binding = RetoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val reto = listaRetos[position]

        //controladores()
        return RecyclerViewHolder(binding)
    }

    private fun obtenerRetoAEliminar(position: Int): String {
        return listaRetos[position].reto // Suponiendo que 'reto' es el campo que deseas pasar
    }

    private fun obtenerIdAEliminar(position: Int): Int {
        return listaRetos[position].id
    }

    override fun getItemCount(): Int {
        return listaRetos.size
    }

    override fun onBindViewHolder(recyclerViewHolder:RecyclerViewHolder, position: Int) {
        val reto = listaRetos[position]


        recyclerViewHolder.bindingItem.deleteIcon.setOnClickListener{
            //Toast.makeText(recyclerViewHolder.itemView.context, "Clic en delete", Toast.LENGTH_SHORT).show()
            val retoAEliminar = obtenerRetoAEliminar(position)
            val idAEliminar = obtenerIdAEliminar(position)

            val bundle = Bundle().apply {
                putString("retoAEliminar", retoAEliminar)
                putInt("idAEliminar", idAEliminar)
            }
            recyclerViewHolder.itemView.findNavController().navigate(R.id.action_fragmentRetos_to_fragmentDeleteReto, bundle)
        }

        recyclerViewHolder.bindingItem.editIcon.setOnClickListener{
            //Toast.makeText(recyclerViewHolder.itemView.context, "Clic en edit", Toast.LENGTH_SHORT).show()
            val fadeIn = AlphaAnimation(1f, 0.5f)
            fadeIn.duration = 5000
            recyclerViewHolder.bindingItem.buttonPanel.startAnimation(fadeIn)
            val retoAEditar = obtenerRetoAEliminar(position)
            val idAEditar = obtenerIdAEliminar(position)

            val bundle = Bundle().apply {
                putString("retoAEditar", retoAEditar)
                putInt("idAEditar", idAEditar)
            }
            //Toast.makeText(recyclerViewHolder.itemView.context, "Clic en edit + $bundle", Toast.LENGTH_SHORT).show()

            recyclerViewHolder.itemView.findNavController().navigate(R.id.action_fragmentRetos_to_fragmentEditReto, bundle)
        }

        recyclerViewHolder.setItemReto(reto)
    }


}