package com.jrexl.daa.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrexl.daa.dataclass.Dpasswordset
import com.jrexl.daa.dataclass.SendOtpRequest
import com.jrexl.daa.dataclass.VerifyOtpRequest
import com.jrexl.daa.objectapp.Opersonalinfo
import kotlinx.coroutines.launch
import java.lang.Exception

class VmPersonalInfo: ViewModel() {

    fun sendotp(mobileno: String, res: (String) -> Unit,  ){
        viewModelScope.launch {
            try {
                val resp = Opersonalinfo.personalapi.sendotp(SendOtpRequest(mobileno))
                if (resp.isSuccessful){
                    res("s")
                }

            }catch (e: Exception){
//                Log.e("sendotp", e.toString())(e.toString())
                res(e.toString())

            }
        }
    }

    fun verfiyotp(mobileno: String, otp:String, onres:(String) -> Unit){
        viewModelScope.launch {
            try {
                val otpres = Opersonalinfo.personalapi.verifyotp(VerifyOtpRequest(mobileno, otp))
                if (otpres.isSuccessful){
                    onres("s")
                }

            }catch (e: kotlin.Exception){
                onres(e.toString())
            }
        }

    }

    fun passwordSet(mobileno: String, pass: String, passres: (String) -> Unit){
        viewModelScope.launch {

            Log.d("PASSWORD_API", "📱 Sending to server → mobileno='$mobileno', pass='$pass'")

            try {
                val pres = Opersonalinfo.personalapi.Ipassset(Dpasswordset(mobileno, pass))

                Log.d("PASSWORD_API", "📡 Response code = ${pres.code()}")
                Log.d("PASSWORD_API", "📡 Response body = ${pres.errorBody()?.string()}")

                if (pres.isSuccessful){
                    passres("s")
                }
                else{
                    passres("error by client")
                }
            }
            catch (e: Exception){
                Log.d("PASSWORD_API", "❌ Exception = ${e.message}")
                passres(e.toString())
            }
        }
    }


}