package com.less.TaTiTuTeTor

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*

class Finisher : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 112
    lateinit var storage: FirebaseStorage
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storage = FirebaseStorage.getInstance()
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
        // The entirity of the DataBase reference acts as the parent node, where the indices acts as the child node.
        database = FirebaseDatabase.getInstance().getReference().child("" + intent.getIntExtra("prizeIndex", -1))
        database.removeValue()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_IMAGE_CAPTURE -> {
                if(resultCode == Activity.RESULT_OK){
                    // Used https://stackoverflow.com/questions/7698409/android-transform-a-bitmap-into-an-input-stream
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    var uploadReference = storage.getReferenceFromUrl("gs://tatitutetor.appspot.com")
                        .child(Calendar.getInstance().getTime().toString())
                    var byteArrayOutputStream = ByteArrayOutputStream()
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream)
                    var byteArrayInputStream = ByteArrayInputStream(byteArrayOutputStream.toByteArray())
                    var uploadTask = uploadReference.putStream(byteArrayInputStream)
                }
            }
        }

        startActivity(Intent(this, MainActivity::class.java))
    }
}