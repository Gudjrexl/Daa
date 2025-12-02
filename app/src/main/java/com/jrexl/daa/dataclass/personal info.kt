package com.jrexl.daa.dataclass

data class ProfileData(
    val name: String,
    val mobileno: String,
    val gender: String,
    val age: Int,
    val orientation: String,
    val hobby: List<String>,
    val preferences: List<String>,
    val religion: String?,
    val status: String?,
    val movies: List<String>,
    val music: List<String>,
    val generics: List<String>,
    val sports: List<String>
)

data class SendOtpRequest(
    val mobileno: String
)

data class VerifyOtpRequest(
    val mobileno: String,
    val otp: String
)

data class Dpasswordset(
    val mobileno: String,
    val pass: String
)
