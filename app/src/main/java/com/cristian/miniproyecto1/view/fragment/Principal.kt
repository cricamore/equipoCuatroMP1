package com.cristian.miniproyecto1.view.fragment

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cristian.miniproyecto1.R
import com.cristian.miniproyecto1.databinding.FragmentPrincipalBinding
import com.cristian.miniproyecto1.view.DialogoReto
import com.cristian.miniproyecto1.view.DialogoReto.Companion.showDialogoReto
import com.cristian.miniproyecto1.viewmodel.InventoryViewModel
import com.cristian.miniproyecto1.viewmodel.RetoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Principal : Fragment() {
    private lateinit var binding: FragmentPrincipalBinding
    private lateinit var circularButton: Button
    private lateinit var bgsound: MediaPlayer
    private lateinit var spinSound: MediaPlayer
    private lateinit var imageViewBotella: ImageView
    private lateinit var countdownText: TextView
    private lateinit var pressText: TextView
    private val inventoryViewModel: InventoryViewModel by viewModels()
    private val retoViewModel: RetoViewModel by viewModels()
    private var currentRotation = 0f
    private var isBottleSpinning = false

    private var isBGSoundEnabled = true
    private lateinit var soundButton: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        soundButton = binding.toolbar.soundButton

        binding.toolbar.shareButton.setOnClickListener {
            share()
        }
        binding.toolbar.rulesButton.setOnClickListener{
            findNavController().navigate(R.id.action_to_fragmentInstrucciones)
            bgsound.stop()
        }

        binding.toolbar.retosButton.setOnClickListener{
            findNavController().navigate(R.id.action_to_fragmentRetos)
            bgsound.stop()
        }

        countdownText.visibility = View.INVISIBLE

        circularButton = binding.button
        startRippleAnimation(circularButton)

        bgsound = MediaPlayer.create(activity, R.raw.bgsound)
        spinSound = MediaPlayer.create(activity, R.raw.girar)

        bgsound.start()
        bgsound.isLooping = true
        bgsound.setVolume(1.0f, 1.0f)

        circularButton.setOnClickListener {
            if (!isBottleSpinning) {
                startBottleSpin()
            }
        }

        soundButton.setOnClickListener {
            isBGSoundEnabled = !isBGSoundEnabled
            toggleBackgroundSound(isBGSoundEnabled)

            val imageResource = if (isBGSoundEnabled){
                R.drawable.baseline_volume_up_24
            } else {
                R.drawable.baseline_volume_off_24
            }

            binding.toolbar.soundButton.setImageResource(imageResource)
        }
        val starButton = binding.toolbar.starButton
        starButton.setOnClickListener {
            openGooglePlayPage()
        }
        
    }

    private fun share() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        val shareBody = "App pico botella\n" +
                "“Solo los valientes lo juegan !!\n" +
                "https://play.google.com/store/apps/details?id=com.nequi.MobileApp&hl=es_419&gl=es"
        val shareSub = "App pico botella"
        intent.putExtra(Intent.EXTRA_SUBJECT, shareSub)
        intent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(intent, "Share using"))
    }

    private fun openGooglePlayPage() {
        val appPackageName = "com.nequi.MobileApp&hl=es_419&gl=es"
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
        } catch (e: android.content.ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }
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
                circularButton.isEnabled = true
                circularButton.visibility = View.VISIBLE
                pressText.visibility = View.VISIBLE
                startRippleAnimation(circularButton)
                countdownText.visibility = View.INVISIBLE
                isBottleSpinning = false
                dialogo()
            }
        }.start()
    }

    private fun dialogo(){
        showDialogoReto(this,inventoryViewModel, retoViewModel){
            toggleBackgroundSound(true)
        }
    }

    private fun playBottleSpinSound() {
        if (!spinSound.isPlaying) {
            spinSound.start()
            toggleBackgroundSound(false)
        }
    }
    private fun stopBottleSpinSound() {
        if (spinSound.isPlaying) {
            spinSound.pause()
            spinSound.seekTo(0)
        }
    }
    private fun toggleBackgroundSound(isEnabled: Boolean) {
        if (isBGSoundEnabled) {
            if (isEnabled) {
                bgsound.start()
            } else {
                bgsound.pause()
                bgsound.seekTo(0)
            }
        }
        else {
            bgsound.pause()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        spinSound.release()
        bgsound.release()
    }

    override fun onPause() {
        super.onPause()
        toggleBackgroundSound(false)
    }

    override fun onResume() {
        super.onResume()
        toggleBackgroundSound(isBGSoundEnabled)
    }



}