package com.elslode.onetwotrip.presentation.fragmentDialogChooseLevelTicket

sealed class StateDialog

object VisibilityBoxEconomy: StateDialog()
object VisibilityBoxBusiness: StateDialog()
object VisibilityButtonEnabled: StateDialog()
class PriceEconomy(val price: String): StateDialog()
class PriceBusiness(val price: String) : StateDialog()




