package com.elslode.onetwotrip.domain

import javax.inject.Inject

class LoadTripsUseCase @Inject constructor(
    private val repository: TicketsRepository
){
    suspend operator fun invoke() = repository.loadTrips()
}