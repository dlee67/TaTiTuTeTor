package com.less.TaTiTuTeTor

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class PrizesAdapter(dataSet: ArrayList<Prize>) : RecyclerView.Adapter<PrizesAdapter.PrizeHolder>() {

    var prizes: ArrayList<Prize> = dataSet

    class PrizeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var prizeView = itemView as Button
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PrizesAdapter.PrizeHolder {
        var prizeView = LayoutInflater.from(parent.context).inflate(R.layout.prize_text_view, parent,false)
        var prizeHolder = PrizeHolder(prizeView as Button)
        return prizeHolder
    }


    override fun onBindViewHolder(holder: PrizesAdapter.PrizeHolder, position: Int) {
        holder.prizeView.setText(prizes.get(position).prizeName)
        holder.prizeView.setOnClickListener {
            AlertDialog.Builder(it.context)
                .setMessage(prizes.get(position).prizeTask)
                .setPositiveButton("START"){_,_ ->
                    it.context.startActivity(
                        Intent(it.context, TaskActivity::class.java))
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return prizes.size
    }
}