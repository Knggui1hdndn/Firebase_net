package com.example.firebase_net.bai3

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.firebase_net.R
import com.example.firebase_net.databinding.ActivityMain3Binding
import com.example.firebase_net.utils.Utils
import com.google.firebase.storage.FirebaseStorage

class Main3Activity : AppCompatActivity() {
    private var uri: Uri? = null
    private val storageRef = FirebaseStorage.getInstance().reference.child("image")
    private fun putImage(uri: Uri, call: (String?) -> Unit) {
        storageRef.putFile(uri).addOnCompleteListener {
            if (it.isSuccessful) {
                storageRef.downloadUrl.addOnCompleteListener {
                    if (it.isSuccessful) call(it.result.toString()) else call(null)
                }
            } else {
                call(null)
            }
        }
    }

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            this.uri = uri
            binding.img.setImageURI(uri)
        }
    private lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            pickImg.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
            progress.visibility = View.INVISIBLE
            btnPush.setOnClickListener {
                progress.visibility = View.VISIBLE
                uri?.let { it1 ->
                    putImage(it1) {
                        progress.visibility = View.INVISIBLE
                        if (it!=null) {
                            Utils.toast(this@Main3Activity,"Thành công")
                        }else{
                            Utils.toast(this@Main3Activity,"Error")
                        }
                    }
                }
            }
        }
    }
}