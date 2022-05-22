package com.elslode.onetwotrip.data.mapper

import com.elslode.onetwotrip.data.database.PriceDbModel
import com.elslode.onetwotrip.data.database.TripDbModel
import com.elslode.onetwotrip.data.database.TicketEntityDbModel
import com.elslode.onetwotrip.domain.Price
import com.elslode.onetwotrip.domain.Trip
import com.elslode.onetwotrip.domain.Ticket
import javax.inject.Inject

class MapperDbToDomain @Inject constructor() {

    fun mapTripDbToEntityTrip(response: TicketEntityDbModel) =
        Ticket(
            id = response.id,
            currency = response.currency,
            prices = mapPriceDbToPrice(response.prices),
            trips = mapTripsListDbToTripsList(response.trips)
        )

    private fun mapPriceDbToPrice(priceDb: List<PriceDbModel>) =
        priceDb.map { Price(type = it.type, amount = it.amount) }

    private fun mapTripsListDbToTripsList(trip: List<TripDbModel>) =
        trip.map { Trip(from = it.from, to = it.to) }
}