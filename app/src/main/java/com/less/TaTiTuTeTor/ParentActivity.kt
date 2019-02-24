package com.less.TaTiTuTeTor

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class ParentActivity : AppCompatActivity(){

    lateinit var storageRef: StorageReference
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.for_parents)

        imageView = findViewById<ImageView>(R.id.child_picture)

        FirebaseDatabase.getInstance().getReferenceFromUrl("https://tatitutetor.firebaseio.com/").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                imageView.setImageResource(android.R.color.transparent)
                Toast.makeText(applicationContext, "Your child has finished a task!", Toast.LENGTH_LONG)
                var localFile = File.createTempFile("images", "png");
                val value = dataSnapshot.getValue() as List<Map<*, *>>
                Thread.sleep(2000)
                for(task in value){
                    var isDone = task.get("done")
                    Log.i("dhl", "isDone is: " + isDone.toString());
                    Log.i("dhl", "where the full URL is: " + "gs://tatitutetor.appspot.com/" + isDone.toString())
                    if(!isDone!!.equals("false")){
                        Log.i("dhl", "In the block of is not false");
                        storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://tatitutetor.appspot.com/").child(isDone.toString())
                        storageRef.getFile(localFile).addOnSuccessListener {
                            Log.i("dhl", "In the block of OnSuccessListener.")
                            var bitMap = BitmapFactory.decodeFile(localFile.absolutePath)
                            imageView.setImageBitmap(bitMap)
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.i("dhl", "" + error.message)
            }
        })
    }
}