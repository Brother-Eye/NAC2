package com.fiap.nac2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_data_base.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener





class DataBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base)

        tvExibicao.text = ""

        btSalvar.setOnClickListener {

            // Write a message to the database
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("message")

            myRef.setValue(etDado.text.toString())

            // Read from the database
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue(String::class.java)
                    tvExibicao.text = value
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value

                }
            })
        }
    }
}
