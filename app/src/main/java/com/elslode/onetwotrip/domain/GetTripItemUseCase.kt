package com.elslode.onetwotrip.domain

import javax.inject.Inject

class GetTripItemUseCase @Inject constructor(
    private val repository: TicketsRepository
) {
    suspend fun getItemTrip(id: Int): TripResponse = repository.getItemTrip(id)
}