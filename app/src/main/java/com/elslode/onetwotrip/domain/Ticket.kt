package com.elslode.onetwotrip.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Ticket(
    val id: Int,
    val currency: String,
    val prices: List<Price>,
    val trips: List<Trip>
)
