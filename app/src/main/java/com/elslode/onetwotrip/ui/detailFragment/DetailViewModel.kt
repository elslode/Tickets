package com.elslode.onetwotrip.ui.detailFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elslode.onetwotrip.domain.TripResponse
import com.elslode.onetwotrip.utils.Constant.BUSSINESS
import com.elslode.onetwotrip.utils.Constant.ECONOM
import com.elslode.onetwotrip.utils.Extension.airportDestination
import com.elslode.onetwotrip.utils.Extension.codeDestination
import com.elslode.onetwotrip.utils.Extension.currencyType
import javax.inject.Inject

class DetailViewModel @Inject constructor() : ViewModel() {

    private val _airportOne = MutableLiveData<String>()
    val airportOne: LiveData<String>
        get() = _airportOne

    private val _airportTwo = MutableLiveData<String>()
    val airportTwo: LiveData<String>
        get() = _airportTwo

    private val _airportThree = MutableLiveData<String>()
    val airportThree: LiveData<String>
        get() = _airportThree

    private val _airportFour = MutableLiveData<String>()
    val airportFour: LiveData<String>
        get() = _airportFour

    private val _cityFrom = MutableLiveData<String>()
    val cityFrom: LiveData<String>
        get() = _cityFrom

    private val _cityTo = MutableLiveData<String>()
    val cityTo: LiveData<String>
        get() = _cityTo

    private val _price = MutableLiveData<String>()
    val price: LiveData<String>
        get() = _price

    private val _classType = MutableLiveData<String>()
    val classType: LiveData<String>
        get() = _classType

    private val _cardVisibilityTwo = MutableLiveData<Boolean>(false)
    val cardVisibilityTwo: LiveData<Boolean>
        get() = _cardVisibilityTwo

    fun setClassFly(typeClass: String ,tripResponse: TripResponse) {
        when(typeClass) {
            ECONOM -> {
                tripResponse.prices?.map {
                    when(it.type) {
                        typeClass -> {
                            tripResponse.apply {
                                _airportOne.value = this.trips?.first()?.from.airportDestination()
                                _airportTwo.value = this.trips?.first()?.to.airportDestination()
                                _airportThree.value = this.trips?.last()?.from.airportDestination()
                                _airportFour.value = this.trips?.last()?.to.airportDestination()
                                _price.value = it.amount.toString() + tripResponse.currency?.currencyType()
                                _classType.value = it.type
                                trips?.size.let { size ->
                                    if (size != null) {
                                        if (size >= 2) {
                                            _cardVisibilityTwo.value = true
                                        }
                                    }
                                }
                                _cityFrom.value = this.trips?.first()?.from.codeDestination()
                                _cityTo.value = this.trips?.last()?.to.codeDestination()
                            }
                        }
                        else -> {}
                    }
                }
            }
            BUSSINESS -> {
                tripResponse.prices?.map {
                    when(it.type) {
                        typeClass -> {
                            tripResponse.apply {
                                _airportOne.value = this.trips?.first()?.from.airportDestination()
                                _airportTwo.value = this.trips?.first()?.to.airportDestination()
                                _airportThree.value = this.trips?.last()?.from.airportDestination()
                                _airportFour.value = this.trips?.last()?.to.airportDestination()
                                _price.value = it.amount.toString() + tripResponse.currency?.currencyType()
                                _classType.value = it.type
                                trips?.size.let { size ->
                                    if (size != null) {
                                        if (size >= 2) {
                                            _cardVisibilityTwo.value = true
                                        }
                                    }
                                }
                                _cityFrom.value = this.trips?.first()?.from.codeDestination()
                                _cityTo.value = this.trips?.last()?.to.codeDestination()
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}