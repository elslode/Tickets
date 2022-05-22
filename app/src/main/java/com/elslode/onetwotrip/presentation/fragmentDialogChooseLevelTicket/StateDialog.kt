package com.elslode.onetwotrip.presentation.fragmentDialogChooseLevelTicket

sealed class StateDialog

object VisibilityBoxEconomy: StateDialog()
object VisibilityBoxBusiness: StateDialog()
object VisibilityButtonEnabled: StateDialog()
data class PriceEconomy(val price: String): StateDialog()
data class PriceBusiness(val price: String) : StateDialog()




