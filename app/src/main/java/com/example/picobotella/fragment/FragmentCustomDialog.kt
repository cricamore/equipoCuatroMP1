package com.example.picobotella.fragment

import android.content.Context
import android.app.Application
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.picobotella.R
import com.example.picobotella.viewmodel.RetoViewModel
import com.example.picobotella.databinding.FragmentCustomDialogBinding
import com.example.picobotella.databinding.FragmentRetosBinding
import com.example.picobotella.model.Reto


class FragmentCustomDialog: Fragment() {
    lateinit var binding: FragmentCustomDialogBinding
    private lateinit var dialog: Dialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomDialogBinding.inflate(inflater)
        binding.lifecycleOwner = this
        dialog =  Dialog(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showInputDialog()
    }
    private val retoViewModel: RetoViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireContext().applicationContext as Application)
            .create(RetoViewModel::class.java)
    }

    fun showInputDialog() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_custom_dialog, null, false)
        dialog.setContentView(binding.root)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(false)
        dialog.show()

        val btnCancel = binding.btnRetoCancelar
        val btnGuardar = binding.btnRetoGuardar
        val etReto = binding.etReto

        etReto.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Nada que hacer
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Nada que hacer
            }

            override fun afterTextChanged(s: Editable?) {
                val inputText = s.toString().trim()

                // Habilita o deshabilita el botón "Guardar" según si el EditText está vacío o no
                btnGuardar.isEnabled = !inputText.isEmpty()
            }
        })

        btnCancel.setOnClickListener {
            Toast.makeText(context, "Has hecho clic en el botón de cancelar.", Toast.LENGTH_SHORT)
                .show()
            dialog.dismiss()
        }

        btnGuardar.setOnClickListener {
            Toast.makeText(context, etReto.text, Toast.LENGTH_SHORT).show()
            saveInvetory()
            findNavController().navigate(R.id.action_fragmentCustomDialog_to_fragmentRetos)
            dialog.dismiss()
        }
    }

    private fun saveInvetory(){
        val retoText = binding.etReto.text.toString()
        val reto = Reto(reto = retoText)
        retoViewModel.saveReto(reto)
        Log.d("test", reto.toString())
        Toast.makeText(context,"Artículo guardado !!", Toast.LENGTH_SHORT).show()
    }

}