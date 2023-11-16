package com.example.picobotella.fragment

import android.app.Application
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.picobotella.R
import com.example.picobotella.databinding.FragmentEditRetoBinding
import com.example.picobotella.model.Reto
import com.example.picobotella.viewmodel.RetoViewModel

class FragmentEditReto : Fragment() {
    lateinit var binding: FragmentEditRetoBinding
    lateinit var retoAEditar : String
    var idAEditar: Int = 0
    private lateinit var dialog: Dialog

    private val retoViewModel: RetoViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireContext().applicationContext as Application)
            .create(RetoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditRetoBinding.inflate(inflater)
        binding.lifecycleOwner = this
        dialog =  Dialog(requireContext())
        retoAEditar = arguments?.getString("retoAEditar") ?: ""
        idAEditar = arguments?.getInt("idAEditar") ?: 0
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        show()
    }

    fun show() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_edit_reto, null, false)
        dialog.setContentView(binding.root)
        findNavController().navigate(R.id.action_fragmentEditReto_to_fragmentRetos)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(false)
        dialog.show()

        binding.etEditReto.text = Editable.Factory.getInstance().newEditable(retoAEditar)


        binding.btnEditGuardar.setOnClickListener{
            val retoEditado = binding.etEditReto.text.toString()
            val reto = Reto(id = idAEditar, reto = retoEditado )
            retoViewModel.updateReto(reto)

            dialog.dismiss()
            findNavController().navigate(R.id.fragmentRetos)
        }

        binding.btnEditCancelar.setOnClickListener{
            dialog.dismiss()
            findNavController().navigate(R.id.fragmentRetos)
        }
    }
}