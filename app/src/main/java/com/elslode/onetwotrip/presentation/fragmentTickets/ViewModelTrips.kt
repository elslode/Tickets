package com.elslode.onetwotrip.presentation.fragmentTickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elslode.onetwotrip.domain.GetTripItemUseCase
import com.elslode.onetwotrip.domain.GetTripsListUseCase
import com.elslode.onetwotrip.domain.LoadTripsUseCase
import com.elslode.onetwotrip.domain.Ticket
import com.elslode.onetwotrip.presentation.SingleLiveEvent
import com.elslode.onetwotrip.presentation.adapter.StateResource
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

class ViewModelTrips @Inject constructor(
    private val getTicketsUseCase: GetTripsListUseCase,
    private val loadTripsUseCase: LoadTripsUseCase,
    private val getTripItemUseCase: GetTripItemUseCase
) : ViewModel() {

    private val _tripItem = SingleLiveEvent<Ticket>()
    val tripItem: SingleLiveEvent<Ticket>
        get() = _tripItem

    private val _ticketsList = MutableLiveData<StateResource<List<Ticket>>>()
    val ticketsList: MutableLiveData<StateResource<List<Ticket>>>
        get() = _ticketsList

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
       _ticketsList.postValue(StateResource.error(throwable.message.toString(), null))
    }

    private val jobGetListTickets = CoroutineScope(Dispatchers.IO + exceptionHandler)

    init {
        _ticketsList.postValue(StateResource.loading(null))
        jobGetListTickets.launch {
            loadTripsUseCase()
            _ticketsList.postValue(StateResource.success(getTicketsUseCase()))
        }
    }

    fun openTicketItem(id: Int) {
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