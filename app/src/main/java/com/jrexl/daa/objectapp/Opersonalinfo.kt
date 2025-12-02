package com.jrexl.daa.objectapp

import com.jrexl.daa.appinterface.Ipersonalinfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Opersonalinfo {
val personalapi: Ipersonalinfo by lazy {
    Retrofit.Builder().baseUrl(urlobject.url)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(
        Ipersonalinfo::class.java)
}
}