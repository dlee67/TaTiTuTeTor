package com.less.TaTiTuTeTor

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class PrizesAdapter(dataSet: ArrayList<String>) : RecyclerView.Adapter<PrizesAdapter.PrizeHolder>() {

    var prizes: ArrayList<String> = dataSet

    class PrizeHolder(itemView: TextView) : RecyclerView.ViewHolder(itemView) {
        var prizeView = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PrizesAdapter.PrizeHolder {
        var textView = LayoutInflater.from(parent.context).inflate(R.layout.prize_text_view, parent,false)
        var prizeHolder = PrizeHolder(textView as TextView)

        return prizeHolder
    }


    override fun onBindViewHolder(holder: PrizesAdapter.PrizeHolder, position: Int) {
        holder.prizeView.setText(prizes.get(position))
    }

    override fun getItemCount(): Int {
        return prizes.size
    }
}