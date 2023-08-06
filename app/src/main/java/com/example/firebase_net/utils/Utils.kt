package com.example.firebase_net.utils

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.example.firebase_net.bai1.AccountInterface

object Utils {
    fun checkEmail(email:String ):Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun toast(context: Context,s:String){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show()
    }
}