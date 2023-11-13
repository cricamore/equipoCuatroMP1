package com.cristian.miniproyecto1.view.fragment

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.widget.Button
import com.cristian.miniproyecto1.R
import com.cristian.miniproyecto1.databinding.FragmentPrincipalBinding

class Principal : Fragment() {
    private lateinit var binding: FragmentPrincipalBinding
    private lateinit var circularButton: Button
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPrincipalBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controladores()
    }

    private fun controladores() {
        binding.toolbar.shareButton.setOnClickListener {
            share()
        }

        circularButton = binding.button
        startRippleAnimation(circularButton)

        mediaPlayer = MediaPlayer.create(activity, R.raw.bgsound)

        mediaPlayer.isLooping = true
        mediaPlayer.start()
        mediaPlayer.setVolume(1.0f, 1.0f)

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

    private fun startRippleAnimation(view: Button) {
        val animationSet = AnimationSet(true)

        for (i in 1..3) {
            val scaleAnimation = ScaleAnimation(
                0f,
                1f,
                0f,
                1f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            scaleAnimation.duration = 500
            scaleAnimation.fillAfter = true
            scaleAnimation.repeatCount = Animation.INFINITE

            animationSet.addAnimation(scaleAnimation)
        }

        view.startAnimation(animationSet)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }



}