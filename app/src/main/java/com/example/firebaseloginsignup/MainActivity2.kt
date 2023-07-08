package com.example.firebaseloginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseloginsignup.databinding.ActivityMain2Binding
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseAuth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            signout()
        }
    }

    private fun signout() {
        firebaseAuth.signOut()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}