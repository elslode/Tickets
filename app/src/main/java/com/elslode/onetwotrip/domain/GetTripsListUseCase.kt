package com.elslode.onetwotrip.domain

import javax.inject.Inject

class GetTripsListUseCase @Inject constructor(
    private val repository: TicketsRepository
) {
    operator fun invoke() = repository.getTicketsFromDb()
}