package com.less.TaTiTuTeTor

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class ParentActivity : AppCompatActivity(){

    lateinit var database: DatabaseReference
    lateinit var storageRef: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.for_parents)

        var imageView = findViewById<ImageView>(R.id.child_picture)

        database = FirebaseDatabase.getInstance().getReference()
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var localFile = File.createTempFile("images", "png");
                val value = dataSnapshot.getValue() as List<Map<*, *>>
                for(task in value){
                    var isDone = task.get("done")
                    Log.i("dhl", "isDone is: " + isDone.toString());
                    Log.i("dhl", "where the full URL is: " + "gs://tatitutetor.appspot.com/" + isDone.toString() + ".png")
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