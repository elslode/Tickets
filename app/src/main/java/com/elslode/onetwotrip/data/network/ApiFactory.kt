package com.elslode.onetwotrip.data.network

import com.elslode.onetwotrip.utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constant.BASE_URL)
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}