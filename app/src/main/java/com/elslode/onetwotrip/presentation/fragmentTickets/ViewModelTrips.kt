package com.elslode.onetwotrip.presentation.fragmentTickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elslode.onetwotrip.domain.GetTripItemUseCase
import com.elslode.onetwotrip.domain.GetTripsListUseCase
import com.elslode.onetwotrip.domain.LoadTripsUseCase
import com.elslode.onetwotrip.domain.Ticket
import kotlinx.coroutines.*
import javax.inject.Inject

class ViewModelTrips @Inject constructor(
    private val getTicketsUseCase: GetTripsListUseCase,
    private val loadTripsUseCase: LoadTripsUseCase,
    private val getTripItemUseCase: GetTripItemUseCase
): ViewModel() {

    val listTrips = getTicketsUseCase()

    private val _tripItem = MutableLiveData<Ticket>()
    val tripItem: LiveData<Ticket>
        get() = _tripItem

    private val jobGetListTickets = CoroutineScope(Dispatchers.IO)

    init {
        jobGetListTickets.launch {
            loadTripsUseCase()
        }
    }

    fun getTicketItem(id: Int){
        viewModelScope.launch {
            val item = getTripItemUseCase.getItemTrip(id)
            _tripItem.value = item
        }
    }

    override fun onCleared() {
        super.onCleared()
        jobGetListTickets.cancel()
    }
}