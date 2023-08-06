package com.example.firebase_net.bai1

import android.util.Log
import com.example.firebase_net.utils.Utils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AccountPresenter(private val view: AccountInterface.View) : AccountInterface.Presenter {
    private val firebaseAuth = FirebaseAuth.getInstance()
    override fun login(email: String, pass: String) {

        if (!Utils.checkEmail(email) || pass.isEmpty()) {
            view.onFailed("Vui lòng nhập đủ đúng thông tin và định dạng")
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            check(it.isSuccessful, "Login thành công", it.exception?.message.toString())
        }
    }

    override fun register(email: String, pass: String) {
        Log.e("ssssssssssssssss",Utils.checkEmail(email).toString())
        if (!Utils.checkEmail(email) || pass.isEmpty()) {
            view.onFailed("Vui lòng nhập đủ đúng thông tin và định dạng")
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
            check(it.isSuccessful, "Đăng kí thành công", it.exception?.message.toString())
        }
    }

    override fun forgotPassword(email: String) {
        if (!Utils.checkEmail(email)) {
            view.onFailed("Vui lòng nhập đủ đúng thông tin và định dạng")
            return
        }
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
            check(it.isSuccessful, "Vui lòng kiểm tra email", it.exception?.message.toString())
        }
    }

    override fun changeEmail(newEmail: String) {
        if (!Utils.checkEmail(newEmail)) {
            view.onFailed("Vui lòng nhập đủ đúng thông tin và định dạng")
            return
        }
        firebaseAuth.currentUser?.apply {
            updateEmail(newEmail).addOnCompleteListener {
                check(it.isSuccessful, "Update thành công", it.exception?.message.toString())
            }
        }
    }

    override fun changePassWord(newPass: String) {
        if (newPass.isEmpty()) {
            view.onFailed("Vui lòng nhập đủ đúng thông tin và định dạng")
            return
        }
        firebaseAuth.currentUser?.apply {
            updatePassword(newPass).addOnCompleteListener {
                check(it.isSuccessful, "Update thành công", it.exception?.message.toString())
            }
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()

    }

    override fun sendPasswordResetEmail(email: String) {
        if (!Utils.checkEmail(email)) {
            view.onFailed("Vui lòng nhập đủ đúng thông tin và định dạng")
            return
        }
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
            check(it.isSuccessful, "Kiểm tra email", it.exception?.message.toString())
        }
    }

    override fun removeUser(call:(Boolean)->Unit) {
         firebaseAuth.currentUser?.delete()?.addOnCompleteListener {
             check(it.isSuccessful, "Xóa thành công", it.exception?.message.toString())
         }
    }

    private fun check(b: Boolean, s: String, e: String) {
        if (b) view.onSuccess(s) else view.onFailed(e)
    }
}