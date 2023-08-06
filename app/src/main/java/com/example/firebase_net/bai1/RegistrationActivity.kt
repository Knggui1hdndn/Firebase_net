package com.example.firebase_net.bai1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase_net.databinding.ActivityRegistrationBinding
import com.example.firebase_net.utils.Utils

class RegistrationActivity : AppCompatActivity(), AccountInterface.View {
    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val presenter = AccountPresenter(this)
        with(binding) {
            btnRegistration.setOnClickListener {
                presenter.register(edtEmail.text.toString().trim(), edtPass.text.toString().trim())
            }

            txtLoginMe.setOnClickListener {
                startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
            }

        }
    }

    override fun onSuccess(mess: String) {
        Utils.toast(this,mess)
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun onFailed(e: String) {
        Utils.toast(this,e)

    }
}