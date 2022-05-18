package com.elslode.onetwotrip.data.network

import com.elslode.onetwotrip.utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
        private const val BASE_URL: String = "https://603e34c648171b0017b2ec55.mockapi.io/"

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val apiService = retrofit.create(ApiService::class.java)
}
