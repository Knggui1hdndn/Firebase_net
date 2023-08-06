package com.example.firebase_net.bai1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase_net.databinding.ActivityMainBinding
import com.example.firebase_net.utils.Utils

class Main1Activity : AppCompatActivity(), AccountInterface.View {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            val accountPresenter = AccountPresenter(this@Main1Activity)
            btnChangeEmail.setOnClickListener {
                edtChange.hint = "New email"
            }
            btnChangePass.setOnClickListener {
                edtChange.hint = "New Pass"
            }
            btnChange.setOnClickListener {
                when (edtChange.hint) {
                    "New email" -> {
                        accountPresenter.changeEmail(edtChange.text.toString().trim())
                    }

                    "New Pass" -> {
                        accountPresenter.changePassWord(edtChange.text.toString().trim())
                    }
                }
            }
            btnSignOut.setOnClickListener {
                accountPresenter.signOut()
                finish()
                startActivity(Intent(this@Main1Activity, LoginActivity::class.java))
            }
            btnRemoveUser.setOnClickListener {
                accountPresenter.removeUser {
                    finish()
                    startActivity(Intent(this@Main1Activity, LoginActivity::class.java))
                }
            }
            btnSendPass.setOnClickListener {
                accountPresenter.sendPasswordResetEmail(edtChange.text.toString().trim())
            }
        }
    }

    override fun onSuccess(mess: String) {
        Utils.toast(this, mess)
    }

    override fun onFailed(e: String) {
        Utils.toast(this, e)

    }
}