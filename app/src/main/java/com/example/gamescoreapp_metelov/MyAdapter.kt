package com.example.gamescoreapp_metelov

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private var data: MutableList<Pair<String, Int>>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(dataPair: Pair<String, Int>) {
            itemView.run {
                findViewById<LinearLayout>(R.id.winnerItemContainer)
                findViewById<TextView>(R.id.tvNameWinnerTeam).text = dataPair.first
                findViewById<TextView>(R.id.tvScore).text =
                    itemView.context.getString(R.string.final_score, dataPair.second)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.winner_recycler_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

