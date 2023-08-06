package com.example.firebase_net.bai1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase_net.databinding.ActivityLoginBinding
import com.example.firebase_net.utils.Utils

class LoginActivity : AppCompatActivity(), AccountInterface.View {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val presenter = AccountPresenter(this)
        with(binding) {
            btnLogin.setOnClickListener {
                presenter.login(edtEmail.text.toString().trim(), edtPass.text.toString().trim())
            }

            txtRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
            }
            txtForgotPass.setOnClickListener {

            }
        }
    }

    override fun onSuccess(mess: String) {
        Utils.toast(this,mess)
        startActivity(Intent(this, Main1Activity::class.java))
    }

    override fun onFailed(e: String) {
        Utils.toast(this,e)

    }
}