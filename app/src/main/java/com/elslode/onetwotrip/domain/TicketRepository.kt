package com.elslode.onetwotrip.domain

import androidx.lifecycle.LiveData

interface TicketRepository {

    fun getTicketsFromDb(): LiveData<List<Ticket>>

    suspend fun loadTrips()

    suspend fun getItemTrip(id: Int): Ticket
}