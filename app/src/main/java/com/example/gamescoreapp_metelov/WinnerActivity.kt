package com.example.gamescoreapp_metelov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamescoreapp_metelov.databinding.ActivityWinnerBinding

class WinnerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWinnerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()

    }

    private fun setupBinding() {
        binding = ActivityWinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}