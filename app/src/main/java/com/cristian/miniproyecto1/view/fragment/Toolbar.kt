package com.cristian.miniproyecto1.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cristian.miniproyecto1.R
import com.cristian.miniproyecto1.databinding.FragmentRetoBinding
import com.cristian.miniproyecto1.databinding.FragmentToolbarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Toolbar : Fragment() {

    private lateinit var binding: FragmentToolbarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToolbarBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controladores()
    }

    private fun controladores() {
        binding.shareButton.setOnClickListener{
            share()
        }
    }

    private fun share() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        val shareBody = "Body"
        val shareSub = "Subject"
        intent.putExtra(Intent.EXTRA_SUBJECT, shareSub)
        intent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(intent, "Share using"))
    }
}