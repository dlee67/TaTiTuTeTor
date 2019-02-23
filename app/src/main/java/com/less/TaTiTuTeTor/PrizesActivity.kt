package com.less.TaTiTuTeTor

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class PrizesActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var prizesAdapter: RecyclerView.Adapter<PrizesAdapter.PrizeHolder>
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prizes_view)

        var games = ArrayList<String>()
        // Better replace the lines below with something more official.
        var gameNames = arrayOf("Halo",
            "Transformers",
            "Avengers",
            "Superman",
            "Mega Man X",
            "Digimon",
            "Pokemon",
            "Bomberman",
            "Dragon Ball Z: Legacy of Guko",
            "C++ for beginners",
            "Starting Out with Java",
            "Fallout 2")
        for(i in gameNames){
            games.add(i)
        }

        recyclerView = findViewById(R.id.prizes_recycler_view)
        layoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(layoutManager)

        prizesAdapter = PrizesAdapter(games)
        recyclerView.setAdapter(prizesAdapter)
    }
}