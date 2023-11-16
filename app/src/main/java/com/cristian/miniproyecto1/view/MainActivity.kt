package com.cristian.miniproyecto1.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cristian.miniproyecto1.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


}