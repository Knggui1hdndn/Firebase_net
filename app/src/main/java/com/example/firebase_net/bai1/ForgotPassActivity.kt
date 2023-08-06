package com.example.firebase_net.bai1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase_net.R
import com.example.firebase_net.databinding.ActivityForgotPassBinding
import com.example.firebase_net.utils.Utils

class ForgotPassActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPassBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityForgotPassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSend.setOnClickListener {
            AccountPresenter(object :AccountInterface.View{
                override fun onSuccess(mess: String) {
                    Utils.toast(this@ForgotPassActivity,mess)
                }

                override fun onFailed(e: String) {
                    Utils.toast(this@ForgotPassActivity,e)

                }
            }).forgotPassword(binding.edtEmail.text.toString().trim())
        }
    }
}