package com.elslode.onetwotrip.presentation.detailFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elslode.onetwotrip.domain.GetTripItemUseCase
import com.elslode.onetwotrip.domain.TypeOfTicket
import com.elslode.onetwotrip.presentation.Extension.airportDestination
import com.elslode.onetwotrip.presentation.Extension.codeDestination
import com.elslode.onetwotrip.presentation.Extension.currencyType
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getTripItemUseCase: GetTripItemUseCase
) : ViewModel() {

    private val _stateDetail = MutableLiveData<StateDetail>()
    val stateDetail: LiveData<StateDetail>
        get() = _stateDetail

    fun setDetailData(typeTicket: String, ticketId: Int) {
        viewModelScope.launch {
            val ticket = getTripItemUseCase.getItemTrip(ticketId)
            when (typeTicket) {
                TypeOfTicket.ECONOM.ticketType -> {
                    _stateDetail.value =
                        NameOfAirportOne(ticket.trips.first().from.airportDestination())
                    _stateDetail.value =
                        NameOfAirportTwo(ticket.trips.first().to.airportDestination())
                    _stateDetail.value =
                        NameOfAirportThree(ticket.trips.last().from.airportDestination())
                    _stateDetail.value =
                        NameOfAirportFour(ticket.trips.last().to.airportDestination())
                    ticket.prices.map {
                        if (it.type == TypeOfTicket.ECONOM.ticketType) {
                            _stateDetail.value =
                                Price(it.amount.toString() + ticket.currency.currencyType())
                            _stateDetail.value = TicketType(it.type)
                        }
                    }
                    ticket.trips.size.let { size ->
                        if (size >= AMOUNT_OF_TICKETS) {
                            _stateDetail.value = VisibilityCardBusiness
                        }
                    }
                    _stateDetail.value = CityFrom(ticket.trips.first().from.codeDestination())
                    _stateDetail.value = CityTo(ticket.trips.last().to.codeDestination())
                }
                TypeOfTicket.BUSSINESS.ticketType -> {
                    _stateDetail.value =
                        NameOfAirportOne(ticket.trips.first().from.airportDestination())
                    _stateDetail.value =
                        NameOfAirportTwo(ticket.trips.first().to.airportDestination())
                    _stateDetail.value =
                        NameOfAirportThree(ticket.trips.last().from.airportDestination())
                    _stateDetail.value =
                        NameOfAirportFour(ticket.trips.last().to.airportDestination())
                    ticket.prices.map {
                        if (it.type == TypeOfTicket.BUSSINESS.ticketType) {
                            _stateDetail.value =
                                Price(it.amount.toString() + ticket.currency.currencyType())
                            _stateDetail.value = TicketType(it.type)
                        }
                    }
                    ticket.trips.size.let { size ->
                        if (size >= AMOUNT_OF_TICKETS) {
                            _stateDetail.value = VisibilityCardBusiness
                        }
                    }
                    _stateDetail.value = CityFrom(ticket.trips.first().from.codeDestination())
                    _stateDetail.value = CityTo(ticket.trips.last().to.codeDestination())
                }
            }
        }
    }

    companion object {
        private const val AMOUNT_OF_TICKETS = 2
    }
}