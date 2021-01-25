package com.example.gamescoreapp_metelov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.example.gamescoreapp_metelov.StartEnter2TeamsActivity.Companion.winnerPairList
import com.example.gamescoreapp_metelov.databinding.ActivityListOfWinnersBinding


class ListOfWinnersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListOfWinnersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupRecyclerView()
        setupOnClickListeners()
    }

    private fun setupBinding() {
        binding = ActivityListOfWinnersBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupRecyclerView() {
        binding.rvWinnersList.run {
            winnerPairList = sortListPairDesc(winnerPairList)
            adapter = MyAdapter(winnerPairList)
            layoutManager = LinearLayoutManager(this@ListOfWinnersActivity)
        }
    }

    private fun sortListPairDesc(list: MutableList<Pair<String, Int>>): MutableList<Pair<String, Int>> {
        val listReversedListReadOnly =
            list.sortedWith(compareBy({ it.second }, { it.first })).asReversed()
        val result: MutableList<Pair<String, Int>> = mutableListOf()
        listReversedListReadOnly.forEach {
            result.add(it)
        }
        return result
    }

    private fun setupOnClickListeners() {
        binding.btnClearList.setOnClickListener {
            MaterialDialog(this).show {
                title(text = "Clear list of Winners")
                message(text = "Do you really want to clear list of Winners ??")
                positiveButton(text = "Yes, I want!") {
                    winnerPairList.clear()
                    binding.rvWinnersList.adapter?.notifyDataSetChanged()
                }
                negativeButton(text = "No, I won't") {
                    dismiss()
                }
            }
        }

        binding.btnCloseList.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        this@ListOfWinnersActivity.finish()
    }
}