package com.example.gamescoreapp_metelov

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.example.gamescoreapp_metelov.databinding.ActivityWinnerBinding
import com.example.gamescoreapp_metelov.StartEnter2TeamsActivity.Companion.winnerPairList
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size


class WinnerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWinnerBinding
    private var winnerName: String = ""
    private var winnerScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupStartValuesOnTheScreen()
        setupListeners()
        loadConfeti()
    }

    private fun setupBinding() {
        binding = ActivityWinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupStartValuesOnTheScreen() {

        val nameTeam1 = intent.getStringExtra("TEAM_FIRST_NAME")
        val nameTeam2 = intent.getStringExtra("TEAM_SECOND_NAME")
        val scoreTeam1 = intent.getStringExtra("TEAM_FIRST_SCORE")
        val scoreTeam2 = intent.getStringExtra("TEAM_SECOND_SCORE")

        var intScoreTeam1 = 0
        var intScoreTeam2 = 0

        binding.tvNameOfTeam1.text = nameTeam1
        binding.tvNameOfTeam2.text = nameTeam2
        binding.tvScoreOfTeam1.text = scoreTeam1
        binding.tvScoreOfTeam2.text = scoreTeam2


        if (scoreTeam1 == scoreTeam2) {
            //setup result if draw
            binding.tvWinnerTeamName.text = getString(R.string.draw_header)
            binding.tvWonOrDraw.text = getString(R.string.draw_body)
        } else {
            if (scoreTeam1?.toInt() != null && scoreTeam2?.toInt() != null) {
                intScoreTeam1 = scoreTeam1.toInt()
                intScoreTeam2 = scoreTeam2.toInt()
            }
            if (intScoreTeam1 > intScoreTeam2) {
                winnerName = nameTeam1.toString()
                winnerScore = intScoreTeam1
                binding.tvWinnerTeamName.text = nameTeam1
                addToWinnersList(Pair(nameTeam1.toString(), intScoreTeam1))
            } else {
                winnerName = nameTeam2.toString()
                winnerScore = intScoreTeam2
                binding.tvWinnerTeamName.text = nameTeam2
                addToWinnersList(Pair(nameTeam2.toString(), intScoreTeam2))
            }
        }
    }

    private fun setupListeners() {
        binding.btnBackHome.setOnClickListener {
            onBackPressed()
        }

        binding.btnShareResults.setOnClickListener {

            val gameResultsToShare: String

            if (binding.tvScoreOfTeam1.text.toString() == binding.tvScoreOfTeam2.text.toString()) {
                gameResultsToShare = getString(
                    R.string.gameDrawResultsToShare,
                    binding.tvNameOfTeam1.text.toString(),
                    binding.tvNameOfTeam2.text.toString(),
                    binding.tvScoreOfTeam1.text.toString(),
                    binding.tvScoreOfTeam2.text.toString()
                )
            } else {
                gameResultsToShare = getString(
                    R.string.gameWinnerResultsToShare,
                    binding.tvNameOfTeam1.text.toString(),
                    binding.tvNameOfTeam2.text.toString(),
                    winnerName,
                    binding.tvScoreOfTeam1.text.toString(),
                    binding.tvScoreOfTeam2.text.toString()
                )
            }


            //Intent to share the text
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, gameResultsToShare)
            startActivity(Intent.createChooser(shareIntent, "Share Game's result via..."))
        }

    }

    private fun addToWinnersList(winnerNameScorePair: Pair<String, Int>) {
        winnerPairList.add(winnerNameScorePair)
        Toast.makeText(
            this@WinnerActivity,
            "Winner added to WinnersList! ",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onBackPressed() {
        MaterialDialog(this).show {
            title(text = "Exit to Main Menu")
            message(text = "Do you really want to back to Main Menu ??")
            positiveButton(text = "Yes. Take me home!") {
                this@WinnerActivity.finish()
            }
            negativeButton(text = "No, I don't") {
                dismiss()
            }
        }
    }

    private fun loadConfeti() {
        binding.viewKonfetti.build()
            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(2000L)
            .addShapes(Shape.RECT, Shape.CIRCLE)
            .addSizes(Size(12))
            .setPosition(-50f, binding.viewKonfetti.width + 50f, -50f, -50f)
            .streamFor(300, 1500L)
    }
}