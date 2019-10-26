package com.fiap.nac2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import android.content.Intent
import android.R.attr.password




class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance();

        btEntrar.setOnClickListener {
            mAuth.signInWithEmailAndPassword(etNome.text.toString(), etSenha.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this,DataBaseActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this,task.exception?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    // ...
                }
        }

        btNovo.setOnClickListener {
            mAuth.createUserWithEmailAndPassword(etNome.text.toString(), etSenha.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this,DataBaseActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {

                        Toast.makeText(
                            this,task.exception?.message,
                            Toast.LENGTH_SHORT
                        ).show()


                    }

                    // ...
                }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.getCurrentUser()
    }
}
