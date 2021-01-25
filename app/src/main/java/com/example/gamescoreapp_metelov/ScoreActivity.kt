package com.example.gamescoreapp_metelov

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.example.gamescoreapp_metelov.databinding.ActivityScoreBinding


class ScoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScoreBinding
    private var isActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupStartValuesOnTheScreen()
        setupListeners()
        reverseTimer(setupGameLengthInSeconds(), binding.timer)
    }

    private fun setupBinding() {
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupStartValuesOnTheScreen() {
        binding.tvNameOfTeam1.text = intent.getStringExtra("TEAM_FIRST_NAME")
        binding.tvNameOfTeam2.text = intent.getStringExtra("TEAM_SECOND_NAME")

        binding.tvScoreOfTeam1.text = "0"
        binding.tvScoreOfTeam2.text = "0"
    }

    private fun setupListeners() {
        binding.btnScores1.setOnClickListener {
            val scoreTeam1BeforeClick: Int = binding.tvScoreOfTeam1.text.toString().toInt()
            binding.tvScoreOfTeam1.text = getString(
                R.string.score_of_the_team_1,
                1 + scoreTeam1BeforeClick
            )
        }

        binding.btnScores2.setOnClickListener {
            val scoreTeam2BeforeClick: Int = binding.tvScoreOfTeam2.text.toString().toInt()
            binding.tvScoreOfTeam2.text = getString(
                R.string.score_of_the_team_2,
                1 + scoreTeam2BeforeClick
            )
        }

        binding.btnCancel.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        MaterialDialog(this).show {
            title(text = "Cancel the Game")
            message(text = "Do you really want to cancel the Game ??")
            positiveButton(text = "Yes. Stop it now!") {
                this@ScoreActivity.run {
                    isActive = false
                    finish()
                }
            }
            negativeButton(text = "No, I don't") {
                dismiss()
            }
        }
    }

    private fun setupGameLengthInSeconds(): Int {
        val sHours: String? = intent.getStringExtra("TIMER_HOURS_VALUE")
        val sMinutes: String? = intent.getStringExtra("TIMER_MINUTES_VALUE")
        val sSeconds: String? = intent.getStringExtra("TIMER_SECONDS_VALUE")

        var gameLengthInSeconds = 0

        if (sHours != "")
            sHours?.toInt()?.let { gameLengthInSeconds += it * 60 * 60 }
        if (sMinutes != "")
            sMinutes?.toInt()?.let { gameLengthInSeconds += it * 60 }
        if (sSeconds != "")
            sSeconds?.toInt()?.let { gameLengthInSeconds += it }

        Log.d("TAGgameLengthInSecondsG", gameLengthInSeconds.toString())
        return gameLengthInSeconds
    }

    private fun reverseTimer(Seconds: Int, tv: TextView) {
        object : CountDownTimer((Seconds * 1000 + 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var seconds = (millisUntilFinished / 1000).toInt()
                val hours = seconds / (60 * 60)
                val tempMint = seconds - hours * 60 * 60
                val minutes = tempMint / 60
                seconds = tempMint - minutes * 60
                tv.text = getString(R.string.time_on_timer, hours, minutes, seconds)
            }

            override fun onFinish() {
                tv.text = getString(R.string.timer_ended)
                binding.btnScores1.isEnabled = false
                binding.btnScores2.isEnabled = false
                if (this@ScoreActivity.isActive) {
                    startWinnerActivity()
                    this@ScoreActivity.finish()
                }
            }
        }.start()
    }


    private fun startWinnerActivity() {
        val intent = Intent(this@ScoreActivity, WinnerActivity::class.java)
        intent.putExtra("TEAM_FIRST_SCORE", binding.tvScoreOfTeam1.text.toString())
        intent.putExtra("TEAM_SECOND_SCORE", binding.tvScoreOfTeam2.text.toString())
        intent.putExtra("TEAM_FIRST_NAME", binding.tvNameOfTeam1.text.toString())
        intent.putExtra("TEAM_SECOND_NAME", binding.tvNameOfTeam2.text.toString())
        startActivity(intent)
    }
}