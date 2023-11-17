package com.cristian.miniproyecto1.view

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cristian.miniproyecto1.databinding.SplashBinding
import com.cristian.miniproyecto1.R

class Splash : AppCompatActivity() {
    private lateinit var binding: SplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        binding = SplashBinding.inflate(layoutInflater)

        setContentView(binding.root)

        animate()


        var handler = Handler(Looper.myLooper()!!)
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            finish()
        }, 5000)
    }

    private fun animate() {
        val rotationAnimator = ObjectAnimator.ofFloat(binding.iconImageView, "rotation", 0f, 360f)
        rotationAnimator.duration = 1000
        rotationAnimator.repeatCount = ObjectAnimator.INFINITE
        rotationAnimator.start()
    }
}
