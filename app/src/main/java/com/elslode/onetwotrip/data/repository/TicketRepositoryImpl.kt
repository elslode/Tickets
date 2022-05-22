package com.elslode.onetwotrip.data.repository

import androidx.lifecycle.LiveData
import com.elslode.onetwotrip.data.database.TicketsDao
import com.elslode.onetwotrip.data.mapper.MapperDbToDomain
import com.elslode.onetwotrip.data.mapper.MapperDtoToDb
import com.elslode.onetwotrip.data.network.ApiService
import com.elslode.onetwotrip.domain.TicketRepository
import com.elslode.onetwotrip.domain.Ticket
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor(
    private val dao: TicketsDao,
    private val apiService: ApiService,
    private val mapperDbToDomain: MapperDbToDomain,
    private val mapperDtoToDb: MapperDtoToDb
) : TicketRepository {

    override fun getTickets(): List<Ticket> {
        val listTicket = dao.getTicketList()
        return listTicket.map {
            mapperDbToDomain.mapTripDbToEntityTrip(it)
        }
    }

    override suspend fun loadTrips() {
        val trips = apiService.getTrips()
        trips.let {
            mapperDtoToDb.mapResponseTripDtoToResponseDb(it).let { listDbModel ->
                dao.saveTicketsList(listDbModel)
            }
        }
    }

    override suspend fun getItemTrip(id: Int): Ticket {
        val dbItem = dao.getTripItem(id)
        return mapperDbToDomain.mapTripDbToEntityTrip(dbItem)
    }

    companion object {
        private const val TAG = "LOAD_DATA_TEST"
    }
}