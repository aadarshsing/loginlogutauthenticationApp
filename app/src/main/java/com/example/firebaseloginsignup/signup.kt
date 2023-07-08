package com.example.firebaseloginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.example.firebaseloginsignup.databinding.ActivitySignupBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class signup : AppCompatActivity() {
    private lateinit var firebaseauth: FirebaseAuth
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseauth= FirebaseAuth.getInstance()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signup.setOnClickListener{
                createUser()

        }
    }
    private fun createUser(){
        val email = binding.username.text.toString()
        val password = binding.password.text.toString()

        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!password.isEmpty()){
                firebaseauth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        Snackbar.make(binding.root,"user registered",Snackbar.LENGTH_SHORT).show()
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }.addOnFailureListener {
                        Snackbar.make(binding.root,"Resistration failed",Snackbar.LENGTH_SHORT).show()
                    }
            }
            else{
                binding.password.setError("empty fields are not allowed")
            }
        }else if(email.isEmpty()){
            binding.username.setError("empty fields are not allowed")
        }
        else
        {
            binding.username.setError("please write correct username")
        }
    }
}