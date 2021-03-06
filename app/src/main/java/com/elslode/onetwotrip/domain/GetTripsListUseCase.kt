package com.elslode.onetwotrip.domain

import javax.inject.Inject

class GetTripsListUseCase @Inject constructor(
    private val repository: TicketRepository
) {
    operator fun invoke() = repository.getTickets()
}