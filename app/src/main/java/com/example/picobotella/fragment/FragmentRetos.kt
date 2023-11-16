package com.example.picobotella.fragment

import android.app.Application
import android.app.Dialog
//import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picobotella.R
import com.example.picobotella.RecyclerAdapter
import com.example.picobotella.databinding.FragmentRetosBinding
import com.example.picobotella.viewmodel.RetoViewModel

class FragmentRetos: Fragment() {
    private lateinit var binding: FragmentRetosBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRetosBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler()
    }
    private val retoViewModel: RetoViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireContext().applicationContext as Application)
            .create(RetoViewModel::class.java)
    }

    fun recycler(){
        binding.addButton.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentRetos_to_fragmentCustomDialog)
        }

        retoViewModel.getListRetos()
        var listaRetos = retoViewModel.listRetos
        val recycler = binding.recyclerview
        val navController = findNavController()
        recycler.layoutManager = LinearLayoutManager(context)
        listaRetos.observe(viewLifecycleOwner, Observer{listaRetos->
            val adapter = RecyclerAdapter(listaRetos, navController)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }
}