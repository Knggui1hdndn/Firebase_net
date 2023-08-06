package com.example.firebase_net.bai1

interface AccountInterface {
    interface View{
        fun onSuccess(mess:String)
        fun onFailed(e:String)
    }
    interface Presenter{
        fun login(email:String,pass:String)
        fun register(email:String,pass:String)
        fun forgotPassword(email: String)
        fun changeEmail(newEmail: String)
        fun changePassWord(newPass: String)
        fun signOut()
        fun sendPasswordResetEmail(email: String)
         fun removeUser(call: (Boolean) -> Unit)
    }
}