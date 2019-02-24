package com.less.TaTiTuTeTor

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PrizesAdapter(dataSet: ArrayList<String>) : RecyclerView.Adapter<PrizesAdapter.PrizeHolder>() {

    var prizes: ArrayList<String> = dataSet
    var database = FirebaseDatabase.getInstance().reference

    class PrizeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var prizeView = itemView as Button
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PrizesAdapter.PrizeHolder {
        var prizeView = LayoutInflater.from(parent.context).inflate(R.layout.prize_text_view, parent,false)
        var prizeHolder = PrizeHolder(prizeView as Button)
        return prizeHolder
    }


    override fun onBindViewHolder(holder: PrizesAdapter.PrizeHolder, position: Int) {
        holder.prizeView.setText(prizes.get(position))
        holder.prizeView.setOnClickListener {
            //Comunicate with Firebase?
        }
    }

    override fun getItemCount(): Int {
        return prizes.size
    }
}