package com.elslode.onetwotrip.domain

import androidx.lifecycle.LiveData


interface TicketRepository {

    fun getTickets(): List<Ticket>

    suspend fun loadTrips()

    suspend fun getItemTrip(id: Int): Ticket
}