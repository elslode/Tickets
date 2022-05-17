package com.elslode.onetwotrip.data.network

import com.google.gson.annotations.SerializedName

data class TripDto(
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String
)