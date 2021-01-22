package com.example.gamescoreapp_metelov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamescoreapp_metelov.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupBinding() {
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}