package com.cristian.miniproyecto1.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.bumptech.glide.Glide
import com.cristian.miniproyecto1.databinding.DialogoRetoBinding
import com.cristian.miniproyecto1.model.Product
import com.cristian.miniproyecto1.repository.InventoryRepository
import com.cristian.miniproyecto1.view.fragment.Principal
import com.cristian.miniproyecto1.viewmodel.InventoryViewModel
import com.cristian.miniproyecto1.viewmodel.RetoViewModel
import com.cristian.miniproyecto1.webservice.ApiService
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.ViewModelLifecycle
import javax.inject.Inject
import kotlin.random.Random

class DialogoReto {
    companion object {

        fun showDialogoReto(
            fragment: Principal,
            inventoryViewModel: InventoryViewModel,
            retoViewModel: RetoViewModel,
            onCloseCallback: () -> Unit
        ) {
            val inflater = LayoutInflater.from(fragment.requireContext())
            val binding = DialogoRetoBinding.inflate(inflater)

            val alertDialog = AlertDialog.Builder(fragment.requireContext()).create()
            alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            alertDialog.setCancelable(false)
            alertDialog.setView(binding.root)

            inventoryViewModel.getProducts()
            fragment.view?.let { view ->
                inventoryViewModel.listProducts.observe(fragment.viewLifecycleOwner) { lista ->
                    val product = lista[Random.nextInt(1,151)]
                    Glide.with(binding.root.context).load(product.image).into(binding.pokemon)
                }
            }

            retoViewModel.getListRetos()
            fragment.view?.let { view ->
                retoViewModel.listRetos.observe(fragment.viewLifecycleOwner){ lista ->
                    val retos = lista[Random.nextInt(lista.size)]
                    binding.reto.text = retos.reto
                }
            }

            binding.btnCerrar.setOnClickListener {
                //Toast.makeText(fragment.requireContext(), "Reto cerrado", Toast.LENGTH_SHORT).show()
                onCloseCallback.invoke()
                alertDialog.dismiss()
            }
            alertDialog.show()



        }

    }
}