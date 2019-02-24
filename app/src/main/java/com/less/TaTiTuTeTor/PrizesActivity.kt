package com.less.TaTiTuTeTor

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener



class PrizesActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var prizesAdapter: RecyclerView.Adapter<PrizesAdapter.PrizeHolder>
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prizes_view)

        database = FirebaseDatabase.getInstance().getReference()

        recyclerView = findViewById(R.id.prizes_recycler_view)
        layoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(layoutManager)

        populatePrizes()

        //prizesAdapter = PrizesAdapter(prizes)
        //recyclerView.setAdapter(prizesAdapter)

    }

    fun populatePrizes(){
        // Read from the database
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue()
                Log.i("dhl", "Value is: " + value);
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.i("dhl", "" + error.message );
            }
        })
    }
}