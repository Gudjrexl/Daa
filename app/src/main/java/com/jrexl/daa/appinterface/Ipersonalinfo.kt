package com.jrexl.daa.appinterface

import com.jrexl.daa.dataclass.Dpasswordset
import com.jrexl.daa.dataclass.SendOtpRequest
import com.jrexl.daa.dataclass.VerifyOtpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Ipersonalinfo {

    @POST("/api/auth/send-otp")
    suspend fun sendotp(@Body sendOtpRequest: SendOtpRequest): Response<Unit>

    @POST("/api/auth/verify-otp")
    suspend fun verifyotp(@Body verifyOtpRequest: VerifyOtpRequest): Response<Unit>

    @POST("/api/auth/set-password")
    suspend fun Ipassset(@Body dpasswordset: Dpasswordset) : Response<Unit>

    @POST("/api/auth/login")
    suspend fun Iloginuser(@Body dpasswordset: Dpasswordset) : Response<Unit>
}



