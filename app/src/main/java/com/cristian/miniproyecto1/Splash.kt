package com.cristian.miniproyecto1

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        val iconImageView = findViewById<ImageView>(R.id.iconImageView)
        val rotation = ObjectAnimator.ofFloat(iconImageView, "rotation", 0f, 360f)
        rotation.duration = 2000

        rotation.interpolator = LinearInterpolator()
        rotation.repeatCount = ObjectAnimator.INFINITE
        rotation.start()
    }
}