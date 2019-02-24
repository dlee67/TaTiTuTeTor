package com.less.TaTiTuTeTor

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ParentActivity : AppCompatActivity(){

    lateinit var database: DatabaseReference
    lateinit var images: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.for_parents)

        database = FirebaseDatabase.getInstance().getReference()
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

            }
            override fun onCancelled(error: DatabaseError) {
                Log.i("dhl", "" + error.message)
            }
        })
    }
}