package com.less.TaTiTuTeTor

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var prizesButton: Button
    lateinit var picturesButton: Button

    val REQUEST_WRITE_AND_CAMERA_PERMISSION = 111;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prizesButton = this.findViewById(R.id.prizes_button)
        prizesButton.setOnClickListener(this)

        picturesButton = this.findViewById(R.id.pictures_button)
        picturesButton.setOnClickListener(this)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            Log.i("dhl", "Asking permission from the user.")
            ActivityCompat.requestPermissions(this,
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA),
                REQUEST_WRITE_AND_CAMERA_PERMISSION)
        }
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.prizes_button -> startActivity(
                Intent(this, PrizesActivity::class.java))
            R.id.pictures_button -> Log.i("dhl", "Dad, checkout where I am at.")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_WRITE_AND_CAMERA_PERMISSION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                }else{
                    Log.i("dhl", "The app won't work properly.")
                }
            }
        }
    }

}
