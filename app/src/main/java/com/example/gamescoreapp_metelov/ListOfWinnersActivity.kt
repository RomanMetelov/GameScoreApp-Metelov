package com.example.gamescoreapp_metelov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamescoreapp_metelov.databinding.ActivityListOfWinnersBinding

class ListOfWinnersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListOfWinnersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupBinding() {
        binding = ActivityListOfWinnersBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}