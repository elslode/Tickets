package com.elslode.onetwotrip.ui.fragmentChooseClassFly

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elslode.onetwotrip.domain.Price
import javax.inject.Inject

class ViewModelDialog @Inject constructor(): ViewModel() {

    private val _checkBoxEconomyVisible = MutableLiveData<Boolean>(false)
    val checkBoxEconomyVisible: LiveData<Boolean>
        get() = _checkBoxEconomyVisible

    private val _checkBoxBussinessVisible = MutableLiveData<Boolean>(false)
    val checkBoxBussinessVisible: LiveData<Boolean>
        get() = _checkBoxBussinessVisible

    private val _buttonEnabledVisible = MutableLiveData<Boolean>(true)
    val buttonEnabledVisible: LiveData<Boolean>
        get() = _buttonEnabledVisible

    private val _priceEconomy = MutableLiveData<String>()
    val priceEconomy: LiveData<String>
        get() = _priceEconomy

    private val _priceBussiness = MutableLiveData<String>()
    val priceBussiness: LiveData<String>
        get() = _priceBussiness

    fun tripAvailablePrice(price: List<Price>?) {
        price?.map { price ->
            when(price.type) {
                "economy" -> {
                    _checkBoxEconomyVisible.value = true
                    _priceEconomy.value = price.amount?.toString()
                }
                "bussiness" -> {
                    _checkBoxBussinessVisible.value = true
                    _priceBussiness.value = price.amount?.toString()
                }
                else  -> {
                    _buttonEnabledVisible.value = false
                }
            }
        }
    }
}