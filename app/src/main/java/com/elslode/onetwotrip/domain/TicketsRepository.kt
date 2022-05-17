package com.elslode.onetwotrip.domain

import androidx.lifecycle.LiveData

interface TicketsRepository {

    fun getTicketsFromDb(): LiveData<List<TripResponse>>

    suspend fun loadTrips()

    suspend fun getItemTrip(id: Int): TripResponse
}