package com.example.firebase_net

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase_net.bai1.LoginActivity
import com.example.firebase_net.bai1.Main1Activity
import com.example.firebase_net.bai3.Main3Activity
import com.example.firebase_net.databinding.ActivityMain2Binding
import com.example.firebase_net.databinding.ActivityMain3Binding
import com.example.firebase_net.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btn1.setOnClickListener {
            startActivity(Intent(this@MainActivity,LoginActivity::class.java))
        }
        binding.btn3.setOnClickListener {
            startActivity(Intent(this@MainActivity,Main3Activity::class.java))
        }
    }
}