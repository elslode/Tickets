package com.elslode.onetwotrip.domain

import javax.inject.Inject

class GetTripItemUseCase @Inject constructor(
    private val repository: TicketRepository
) {
    suspend fun getItemTrip(id: Int): Ticket = repository.getItemTrip(id)
}