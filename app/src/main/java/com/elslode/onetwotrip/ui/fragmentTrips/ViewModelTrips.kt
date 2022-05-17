package com.elslode.onetwotrip.ui.fragmentTrips

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elslode.onetwotrip.domain.GetTripItemUseCase
import com.elslode.onetwotrip.domain.GetTripsListUseCase
import com.elslode.onetwotrip.domain.LoadTripsUseCase
import com.elslode.onetwotrip.domain.TripResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelTrips @Inject constructor(
    private val getTicketsUseCase: GetTripsListUseCase,
    private val loadTripsUseCase: LoadTripsUseCase,
    private val getTripItemUseCase: GetTripItemUseCase
): ViewModel() {

    val listTrips = getTicketsUseCase()

    private val _tripItem = MutableLiveData<TripResponse>()
    val tripItem: LiveData<TripResponse>
        get() = _tripItem

    init {
        CoroutineScope(Dispatchers.IO).launch {
            loadTripsUseCase()
        }
    }

    fun getTripItem(tripId: Int){
        viewModelScope.launch {
            val item = getTripItemUseCase.getItemTrip(tripId)
            _tripItem.value = item
        }
    }
}