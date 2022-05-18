package com.elslode.onetwotrip.data.mapper

import com.elslode.onetwotrip.data.database.PriceDbModel
import com.elslode.onetwotrip.data.database.TripDbModel
import com.elslode.onetwotrip.data.database.TicketEntityDbModel
import com.elslode.onetwotrip.data.network.PriceDto
import com.elslode.onetwotrip.data.network.TripDto
import com.elslode.onetwotrip.data.network.TripResponseDto
import javax.inject.Inject

class MapperDtoToDb @Inject constructor() {

    fun mapResponseTripDtoToResponseDb(response: List<TripResponseDto>) =
        response.map {
            TicketEntityDbModel(
                id = it.id,
                currency = it.currency,
                prices = mapPriceDtoToPriceDb(it.prices),
                trips = mapTripsListDtoToTripsListDb(it.trips)
            )
        }

    private fun mapPriceDtoToPriceDb(priceDto: List<PriceDto>) =
        priceDto.map { PriceDbModel(type = it.type, amount = it.amount) }

    private fun mapTripsListDtoToTripsListDb(trip: List<TripDto>) =
        trip.map { TripDbModel(from = it.from, to = it.to) }

}