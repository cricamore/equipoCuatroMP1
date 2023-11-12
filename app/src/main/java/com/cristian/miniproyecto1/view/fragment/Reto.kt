package com.cristian.miniproyecto1.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.cristian.miniproyecto1.R
import com.cristian.miniproyecto1.databinding.FragmentRetoBinding
import com.cristian.miniproyecto1.databinding.ToolbarBinding
import com.cristian.miniproyecto1.viewmodel.InventoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Reto : Fragment() {

    private lateinit var binding: FragmentRetoBinding
    private val inventoryViewModel: InventoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRetoBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerViewModel()
    }

    private fun observerViewModel() {
        observerListProduct()
    }

    private fun observerListProduct() {
        inventoryViewModel.getProducts()
        inventoryViewModel.listProducts.observe(viewLifecycleOwner){ lista ->

            val product = lista[2]
            Glide.with(binding.root.context).load(product.image).into(binding.pokemon)
        }
    }

}