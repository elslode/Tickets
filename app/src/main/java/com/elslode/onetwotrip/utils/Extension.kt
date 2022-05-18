package com.elslode.onetwotrip.utils

import com.elslode.onetwotrip.domain.Price
import com.elslode.onetwotrip.domain.Trip
import com.elslode.onetwotrip.utils.Constant.ECONOM

object Extension {

    private const val RUBLE = "RUB"
    private const val DOLLAR = "USD"
    private const val EURO = "EUR"

    fun String.currencyType(): String =
        when (this) {
            RUBLE -> "₽"
            DOLLAR -> "$"
            EURO -> "€"
            else -> {
                ""
            }
        }

    fun List<Price>.price(): String {
        var value = ""
        for (i in this) {
            value = if (i.type == ECONOM) {
                i.amount.toString()
            } else {
                i.amount.toString()
            }
        }
        return value
    }

    fun List<Trip>.transfer(): String {
        var value = ""
        if (this.size >= 2) {
            value = "${this.size - 1} ${"пересадка"}"
        } else {
            value = "Без пересадок"
        }
        return value
    }

    fun String?.codeDestination() =
        when (this) {
            "SVO" -> "Москва"
            "HND" -> "Токио"
            "NRT" -> "Токио"
            "EWR" -> "Нью-Йорк"
            "DME" -> "Москва"
            "DOH" -> "Доха"
            "JFK" -> "Нью-Йорк"
            "LHR" -> "Лондон"
            "FRA" -> "Франкфурт-на-Майне"
            else -> ""
        }

    fun String?.airportDestination() =
        when (this) {
            "SVO" -> "Sheremetyevo"
            "HND" -> "Haneda Airport"
            "NRT" -> "Narita International Airport"
            "EWR" -> "Newark Liberty International"
            "DME" -> "Domodedovo"
            "DOH" -> "Doha"
            "JFK" -> "John F Kennedy International"
            "LHR" -> "Heathrow"
            "FRA" -> "Frankfurt International Airport"
            else -> ""
        }
}