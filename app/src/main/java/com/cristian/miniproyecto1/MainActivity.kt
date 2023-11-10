package com.cristian.miniproyecto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.cristian.miniproyecto1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val shareButton: ImageView = binding.toolbar.shareButton

        shareButton.setOnClickListener {
            share()
        }

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


}