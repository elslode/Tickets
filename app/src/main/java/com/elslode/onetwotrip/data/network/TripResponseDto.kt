package com.elslode.onetwotrip.data.network

import androidx.room.PrimaryKey
import com.elslode.onetwotrip.domain.Price
import com.elslode.onetwotrip.domain.Trip
import com.google.gson.annotations.SerializedName

data class TripResponseDto(
    val id: Int,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("prices")
    val prices: List<PriceDto>,
    @SerializedName("trips")
    val trips: List<TripDto>
)