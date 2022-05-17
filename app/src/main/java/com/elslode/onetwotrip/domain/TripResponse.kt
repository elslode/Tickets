package com.elslode.onetwotrip.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TripResponse(
    val id: Int,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("prices")
    val prices: List<Price>? = null,
    @SerializedName("trips")
    val trips: List<Trip>? = null
): Parcelable
