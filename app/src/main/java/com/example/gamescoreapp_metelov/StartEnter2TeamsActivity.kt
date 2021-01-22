package com.example.gamescoreapp_metelov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamescoreapp_metelov.databinding.ActivityStartEnter2TeamsBinding
import java.util.zip.Inflater

class StartEnter2TeamsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartEnter2TeamsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupBinding() {
        binding = ActivityStartEnter2TeamsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}