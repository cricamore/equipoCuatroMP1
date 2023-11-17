package com.cristian.miniproyecto1.view.fragment

import android.app.Application
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.cristian.miniproyecto1.R
import com.cristian.miniproyecto1.databinding.FragmentDeleteRetoBinding
import com.cristian.miniproyecto1.viewmodel.RetoViewModel
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.cristian.miniproyecto1.model.Reto

class FragmentDeleteReto : Fragment() {
    lateinit var binding: FragmentDeleteRetoBinding
    lateinit var retoAEliminar : String
    var idAEliminar: Int = 0
    private lateinit var dialog: Dialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeleteRetoBinding.inflate(inflater)
        binding.lifecycleOwner = this
        dialog =  Dialog(requireContext())
        retoAEliminar = arguments?.getString("retoAEliminar") ?: ""
        idAEliminar = arguments?.getInt("idAEliminar") ?: 0

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        show()
    }

    private val retoViewModel: RetoViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireContext().applicationContext as Application)
            .create(RetoViewModel::class.java)
    }

    fun show() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_delete_reto, null, false)
        dialog.setContentView(binding.root)
        findNavController().navigate(R.id.action_fragmentDeleteReto_to_fragmentRetos)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(false)
        dialog.show()

        binding.tvRetoDesc.text = retoAEliminar

        val reto = Reto(id = idAEliminar, reto = retoAEliminar )

        binding.btnYes.setOnClickListener{
            retoViewModel.deleteReto(reto)
            dialog.dismiss()
            findNavController().navigate(R.id.fragmentRetos)
        }

        binding.btnNo.setOnClickListener{
            dialog.dismiss()
            findNavController().navigate(R.id.fragmentRetos)
        }
    }

}