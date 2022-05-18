package com.elslode.onetwotrip.presentation.fragmentDialogChooseLevelTicket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elslode.onetwotrip.domain.GetTripItemUseCase
import com.elslode.onetwotrip.domain.Ticket
import com.elslode.onetwotrip.utils.Constant
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelDialog @Inject constructor(
    val getTicketItemUseCase: GetTripItemUseCase
) : ViewModel() {

    private val _stateDialog = MutableLiveData<StateDialog>()
    val stateDialog: LiveData<StateDialog>
        get() = _stateDialog

    private var itemTicket: Ticket? = null

    fun getItem(id: Int) {
        viewModelScope.launch {
            val item = getTicketItemUseCase.getItemTrip(id)
            itemTicket = item
            item.prices.apply {
                this.map {
                    when (it.type) {
                        Constant.ECONOM -> {
                            _stateDialog.value = VisibilityBoxEconomy
                            _stateDialog.value = PriceEconomy(it.amount.toString())
                        }
                        Constant.BUSSINESS -> {
                            _stateDialog.value = VisibilityBoxBusiness
                            _stateDialog.value = PriceBusiness(it.amount.toString())
                        }
                        else -> {
                            _stateDialog.value = VisibilityButtonEnabled
                        }
                    }
                }
            }
        }
    }

}