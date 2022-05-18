package com.elslode.onetwotrip.presentation.detailFragment

sealed class StateDetail()

class TicketType(val type: String): StateDetail()
class Price(val price: String): StateDetail()
object VisibilityCardBusiness: StateDetail()
object VisibilityCardEconomy: StateDetail()
class NameOfAirportOne(val airportName: String): StateDetail()
class NameOfAirportTwo(val airportName: String): StateDetail()
class NameOfAirportThree(val airportName: String): StateDetail()
class NameOfAirportFour(val airportName: String): StateDetail()
class CityFrom(val city: String): StateDetail()
class CityTo(val city: String): StateDetail()

