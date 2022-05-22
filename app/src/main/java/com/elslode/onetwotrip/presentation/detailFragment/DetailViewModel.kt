package com.elslode.onetwotrip.presentation.detailFragment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elslode.onetwotrip.domain.GetTripItemUseCase
import com.elslode.onetwotrip.domain.Ticket
import com.elslode.onetwotrip.domain.TypeOfTicket
import com.elslode.onetwotrip.presentation.adapter.StateResource
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getTripItemUseCase: GetTripItemUseCase,
    private val context: Application
) : ViewModel() {

    private val _stateDetail = MutableLiveData<StateResource<Ticket>>()
    val stateDetail: LiveData<StateResource<Ticket>>
        get() = _stateDetail

    fun setDetailData(typeTicket: String, ticketId: Int) {
        viewModelScope.launch {
            _stateDetail.postValue(StateResource.loading(null))
            val ticket = getTripItemUseCase.getItemTrip(ticketId)
            try {
                _stateDetail.postValue(StateResource.success(ticket))
            } catch (e: Exception) {
                _stateDetail.postValue(StateResource.error(e.message.toString(), null))
            }
        }
    }

    private fun valueInTicket(ticket: Ticket) {

//        _stateDetail.value =
//            NameOfAirportTwo(ticket.trips.first().to.airportDestination(context))
//        _stateDetail.value =
//            NameOfAirportThree(ticket.trips.last().from.airportDestination(context))
//        _stateDetail.value =
//            NameOfAirportFour(ticket.trips.last().to.airportDestination(context))
//        ticket.prices.map {
//            if (it.type == type) {
//                _stateDetail.value =
//                    Price(it.amount.toString() + ticket.currency)
//                _stateDetail.value = TicketType(it.type)
//            }
//        }
//        ticket.trips.size.let { size ->
//            if (size >= AMOUNT_OF_TICKETS) {
//                _stateDetail.value = VisibilityCardBusiness
//            }
//        }
//        _stateDetail.value = CityFrom(ticket.trips.first().from.codeDestination(context))
//        _stateDetail.value = CityTo(ticket.trips.last().to.codeDestination(context))
    }

    companion object {
        private const val AMOUNT_OF_TICKETS = 2
    }
}