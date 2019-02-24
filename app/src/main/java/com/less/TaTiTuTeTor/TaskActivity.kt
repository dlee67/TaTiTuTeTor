package com.less.TaTiTuTeTor

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.graphics.Bitmap
import android.os.SystemClock
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.Chronometer;
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit

class TaskActivity : AppCompatActivity(){

    val REQUEST_IMAGE_CAPTURE = 112
    var hoursInMilli: Long = 0

    lateinit var chronometer: Chronometer
    lateinit var storage: FirebaseStorage
    lateinit var stopTimerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_layout)
        var time = intent.getStringExtra("requiredHours").toLong()
        hoursInMilli = TimeUnit.HOURS.toMillis(time)

        Log.i("dhl", "Time right now: " + hoursInMilli)

        storage = FirebaseStorage.getInstance()
        chronometer = findViewById(R.id.task_timer)
        stopTimerButton = findViewById(R.id.stop_timer_button)

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_IMAGE_CAPTURE -> {
                if(resultCode == Activity.RESULT_OK){
                    // Used https://stackoverflow.com/questions/7698409/android-transform-a-bitmap-into-an-input-stream
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    var uploadReference = storage.getReferenceFromUrl("gs://tatitutetor.appspot.com")
                            .child("example.png")
                    var byteArrayOutputStream = ByteArrayOutputStream()
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream)
                    var byteArrayInputStream = ByteArrayInputStream(byteArrayOutputStream.toByteArray())
                    var uploadTask = uploadReference.putStream(byteArrayInputStream)
                }

                startTimer()
            }
        }
    }

    fun startTimer(){
        chronometer.setBase(SystemClock.elapsedRealtime() + hoursInMilli)
        chronometer.isCountDown = true
        chronometer.start()
        Log.i("dhl", "Elapsed real time: " + SystemClock.elapsedRealtime())
        Log.i("dhl", "getBase() currently: " + chronometer.getBase())
        Log.i("dhl", "Display time: " + 1)
    }
}