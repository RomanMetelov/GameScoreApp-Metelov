package com.example.gamescoreapp_metelov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.example.gamescoreapp_metelov.databinding.ActivityStartEnter2TeamsBinding

class StartEnter2TeamsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartEnter2TeamsBinding

    companion object {
        var winnerPairList: MutableList<Pair<String, Int>> = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupOnClickListeners()
    }

    private fun setupBinding() {
        binding = ActivityStartEnter2TeamsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupOnClickListeners() {
        binding.btnStartGame.setOnClickListener {
            //cheking input data, if OK -> startScoreActivity

            val hours = binding.etHours.text.toString()
            val minutes = binding.etMinutes.text.toString()
            val seconds = binding.etSeconds.text.toString()

            val team1Name = binding.etTeams1name.text.toString()
            val team2Name = binding.etTeams2name.text.toString()

            val emptyString = ""

            if (hours == emptyString
                && minutes == emptyString
                && seconds == emptyString
            ) {
                Toast.makeText(
                    this@StartEnter2TeamsActivity,
                    "You should set timer first! ",
                    Toast.LENGTH_LONG
                ).show()
            } else if (team1Name == emptyString
                || team2Name == emptyString
            ) {
                Toast.makeText(
                    this@StartEnter2TeamsActivity,
                    "You should set team's name! ",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(this@StartEnter2TeamsActivity, ScoreActivity::class.java)
                intent.putExtra("TIMER_HOURS_VALUE", hours)
                intent.putExtra("TIMER_MINUTES_VALUE", minutes)
                intent.putExtra("TIMER_SECONDS_VALUE", seconds)
                intent.putExtra("TEAM_FIRST_NAME", team1Name)
                intent.putExtra("TEAM_SECOND_NAME", team2Name)
                startActivity(intent)
            }
        }

        binding.btnListOfWinners.setOnClickListener {
            startListOfWinnersActivity()
        }

        binding.btnExitApp.setOnClickListener {
            MaterialDialog(this).show {
                title(text = "Exit")
                message(text = "Do you want to exit ??")
                positiveButton(text = "Yes. Exit now!") {
                    finish()
                }
                negativeButton(text = "Not now") {
                    dismiss()
                }
            }
        }
    }

    private fun startListOfWinnersActivity() {
        val intent = Intent(this@StartEnter2TeamsActivity, ListOfWinnersActivity::class.java)
        startActivity(intent)
    }
}