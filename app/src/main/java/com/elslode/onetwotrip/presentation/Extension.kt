package com.elslode.onetwotrip.presentation

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.elslode.onetwotrip.R
import com.elslode.onetwotrip.domain.Price
import com.elslode.onetwotrip.domain.Trip
import com.elslode.onetwotrip.domain.TypeOfTicket
import com.elslode.onetwotrip.presentation.Extension.EMPTY

object Extension {

    fun String.currencyType(context: Application): String =
        when (this) {
            RUBLE -> R.string.ruble.getString(context)
            DOLLAR -> R.string.dollar.getString(context)
            EURO -> R.string.euro.getString(context)
            else -> {
                EMPTY
            }
        }

    fun List<Price>.price(): String {
        var value = EMPTY
        for (i in this) {
            value = if (i.type == TypeOfTicket.ECONOM.ticketType) {
                i.amount.toString()
            } else {
                i.amount.toString()
            }
        }
        return value
    }

    fun List<Trip>.transfer(context: Application, size: Int): String {
        return context.resources.getQuantityString(R.plurals.airport, size - 1, size -1)
    }

    fun String?.codeDestination(context: Application) =
        when (this) {
            R.string.svo.getString(context) -> R.string.msk.getString(context)
            R.string.hnd.getString(context) -> R.string.tokio.getString(context)
            R.string.nrt.getString(context) -> R.string.tokio.getString(context)
            R.string.ewr.getString(context) -> R.string.nyc.getString(context)
            R.string.dme.getString(context) -> R.string.msk.getString(context)
            R.string.doh.getString(context) -> R.string.doha_city.getString(context)
            R.string.jfk.getString(context) -> R.string.nyc.getString(context)
            R.string.lhr.getString(context) -> R.string.london.getString(context)
            R.string.fra.getString(context) -> R.string.frankfurt.getString(context)
            else -> EMPTY
        }

    fun String?.airportDestination(context: Application) =
        when (this) {
            R.string.svo.getString(context) -> R.string.sheremetyevo.getString(context)
            R.string.hnd.getString(context) -> R.string.haneda.getString(context)
            R.string.nrt.getString(context) -> R.string.narita.getString(context)
            R.string.ewr.getString(context) -> R.string.newark.getString(context)
            R.string.dme.getString(context) -> R.string.domodedovo.getString(context)
            R.string.doh.getString(context) -> R.string.doha.getString(context)
            R.string.jfk.getString(context) -> R.string.john_f_kennedy.getString(context)
            R.string.lhr.getString(context) -> R.string.heathrow.getString(context)
            R.string.fra.getString(context) -> R.string.frankf.getString(context)
            else -> EMPTY
        }

    private const val RUBLE = "RUB"
    private const val DOLLAR = "USD"
    private const val EURO = "EUR"
    const val EMPTY = ""
}

@JvmOverloads
fun Int.getString(context: Context, default: String = EMPTY): String = try {
    context.resources.getString(this)
} catch (e: Resources.NotFoundException) {
    default
}

