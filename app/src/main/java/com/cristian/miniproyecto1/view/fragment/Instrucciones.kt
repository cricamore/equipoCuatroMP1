package com.cristian.miniproyecto1.view.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cristian.miniproyecto1.R
import com.cristian.miniproyecto1.databinding.FragmentInstruccionesBinding

class Instrucciones : Fragment() {
    private lateinit var binding: FragmentInstruccionesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInstruccionesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controllers()
        animate()
    }
    fun controllers(){
        binding.btnBack.setOnClickListener{
            findNavController().navigate(R.id.action_to_principal)
        }

    }

    private fun animate() {
        val rotationAnimator = ObjectAnimator.ofFloat(binding.ivPersona, "rotation", 0f, 360f)
        rotationAnimator.duration = 1000
        rotationAnimator.repeatCount = ObjectAnimator.INFINITE
        rotationAnimator.start()
    }
}