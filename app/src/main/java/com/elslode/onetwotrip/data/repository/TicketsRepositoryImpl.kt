package com.elslode.onetwotrip.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.elslode.onetwotrip.data.database.TicketsDao
import com.elslode.onetwotrip.data.database.TripsResponseDbModel
import com.elslode.onetwotrip.data.mapper.MapperDbToDomain
import com.elslode.onetwotrip.data.mapper.MapperDtoToDb
import com.elslode.onetwotrip.data.network.ApiService
import com.elslode.onetwotrip.domain.TicketsRepository
import com.elslode.onetwotrip.domain.TripResponse
import javax.inject.Inject

class TicketsRepositoryImpl @Inject constructor(
    private val application: Application,
    private val dao: TicketsDao,
    private val apiService: ApiService,
    private val mapperDbToDomain: MapperDbToDomain,
    private val mapperDtoToDb: MapperDtoToDb
) : TicketsRepository {

    private var autoIncrementId = 0

    override fun getTicketsFromDb(): LiveData<List<TripResponse>> {
        return Transformations.map(dao.getTicketList()) { listTrips ->
            listTrips.map {
                mapperDbToDomain.mapResponseTripDbToResponseTrip(it)
            }
        }
    }

    override suspend fun loadTrips() {
        val trips = apiService.getTrips()
        trips.let {
            mapperDtoToDb.mapResponseTripDtoToResponseDb(it).let { listDbModel ->
                for (i in listDbModel) {
                    if (i.id != TripsResponseDbModel.UNDEFINED_ID) {
                        i.id = autoIncrementId++
                        dao.saveTicketsList(i)
                    }
                }
            }
        }
    }

    override suspend fun getItemTrip(id: Int): TripResponse {
        val dbItem = dao.getTripItem(id)
        return mapperDbToDomain.mapResponseTripDbToResponseTrip(dbItem)
    }

    companion object {
        private const val TAG = "LOAD_DATA_TEST"
    }
}