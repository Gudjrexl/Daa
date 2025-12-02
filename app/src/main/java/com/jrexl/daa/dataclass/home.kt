package com.jrexl.daa.dataclass

data class PersonCard(
    val name: String,
    val age: Int,
    val gender: String,
    val images: List<String>,
    val isRightNow: Boolean
)

data class statuss( val name: String,
    val mediaimg: String,
    val isvideo: Boolean)

