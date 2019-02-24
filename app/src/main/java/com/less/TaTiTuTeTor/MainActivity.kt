package com.less.TaTiTuTeTor

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var prizesButton: Button
    lateinit var picturesButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prizesButton = this.findViewById(R.id.prizes_button)
        prizesButton.setOnClickListener(this)

        picturesButton = this.findViewById(R.id.pictures_button)
        picturesButton.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.prizes_button -> startActivity(
                Intent(this, PrizesActivity::class.java))
            R.id.pictures_button -> Log.i("dhl", "Dad, checkout where I am at.")
        }
    }
}
