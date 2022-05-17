package com.elslode.onetwotrip.data.network

import retrofit2.http.GET

interface ApiService {

    @GET("ott/search")
    suspend fun getTrips(): List<TripResponseDto>
}