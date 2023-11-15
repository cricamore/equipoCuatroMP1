package com.cristian.miniproyecto1.view.fragment

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.cristian.miniproyecto1.R
import com.cristian.miniproyecto1.databinding.FragmentPrincipalBinding

class Principal : Fragment() {
    private lateinit var binding: FragmentPrincipalBinding
    private lateinit var circularButton: Button
    private lateinit var bgsound: MediaPlayer
    private lateinit var spinSound: MediaPlayer
    private lateinit var imageViewBotella: ImageView
    private lateinit var countdownText: TextView
    private lateinit var pressText: TextView
    private var currentRotation = 0f
    private var isBottleSpinning = false


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
        imageViewBotella = binding.imageViewBotella
        pressText = binding.pressText
        countdownText = binding.textViewContador

        binding.toolbar.shareButton.setOnClickListener {
            share()
        }
        binding.toolbar.rulesButton.setOnClickListener{
            findNavController().navigate(R.id.action_to_fragmentInstrucciones)
            bgsound.stop()
        }

        countdownText.visibility = View.INVISIBLE

        circularButton = binding.button
        startRippleAnimation(circularButton)

        bgsound = MediaPlayer.create(activity, R.raw.bgsound)
        spinSound = MediaPlayer.create(activity, R.raw.girar)

        bgsound.isLooping = true
        bgsound.start()
        bgsound.setVolume(1.0f, 1.0f)

        circularButton.setOnClickListener {
            if (!isBottleSpinning) {
                startBottleSpin()
            }
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

    private fun startBottleSpin() {
        isBottleSpinning = true
        circularButton.isEnabled = false
        circularButton.clearAnimation()
        circularButton.visibility = View.INVISIBLE

        pressText.visibility = View.INVISIBLE

        val randomDegrees = (Math.random() * 360).toFloat()
        val rotateAnimation = RotateAnimation(
            currentRotation,
            3600f + randomDegrees + currentRotation,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotateAnimation.duration = 5000
        rotateAnimation.fillAfter = true

        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                playBottleSpinSound()
            }

            override fun onAnimationEnd(animation: Animation?) {
                stopBottleSpinSound()
                circularButton.isEnabled = true
                circularButton.visibility = View.VISIBLE
                pressText.visibility = View.VISIBLE
                startRippleAnimation(circularButton)
                showCountdownText()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
        currentRotation += 3600f + randomDegrees

        imageViewBotella.startAnimation(rotateAnimation)
    }

    private fun showCountdownText() {
        countdownText.visibility = View.VISIBLE

        object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countdownText.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                countdownText.visibility = View.INVISIBLE
                isBottleSpinning = false
            }
        }.start()
    }

    private fun playBottleSpinSound() {
        if (!spinSound.isPlaying) {
            spinSound.start()
            bgsound.stop()
            bgsound.seekTo(0)
        }
    }

    private fun stopBottleSpinSound() {
        if (spinSound.isPlaying) {
            spinSound.pause()
            bgsound.start()
            spinSound.seekTo(0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        spinSound.release()
    }


}