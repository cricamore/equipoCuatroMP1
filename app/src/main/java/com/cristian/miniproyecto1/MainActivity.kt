package com.cristian.miniproyecto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.cristian.miniproyecto1.databinding.ActivityMainBinding
import android.media.MediaPlayer

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var circularButton: Button
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val shareButton: ImageView = binding.toolbar.shareButton

        circularButton = binding.button
        startRippleAnimation(circularButton)

        shareButton.setOnClickListener {
            share()
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.bgsound)

        mediaPlayer.isLooping = true
        mediaPlayer.start()
        mediaPlayer.setVolume(1.0f, 1.0f)

    }

    private fun share(){
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