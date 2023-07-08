package com.example.firebaseloginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.example.firebaseloginsignup.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseauth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseauth= FirebaseAuth.getInstance()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.login.setOnClickListener{
            loginuser()
        }

        binding.signup.setOnClickListener {
            val intent = Intent(this,signup::class.java)
            startActivity(intent)
        }
    }
    private fun loginuser(){
        val email = binding.username.text.toString()
        val password = binding.password.text.toString()
        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!password.isEmpty()){
                firebaseauth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        Snackbar.make(binding.root,"login successful",Snackbar.LENGTH_SHORT).show()
                        val intent = Intent(this,MainActivity2::class.java)
                        startActivity(intent)
                    }.addOnFailureListener {
                        Snackbar.make(binding.root,"incorrect email and password",Snackbar.LENGTH_SHORT).show()
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