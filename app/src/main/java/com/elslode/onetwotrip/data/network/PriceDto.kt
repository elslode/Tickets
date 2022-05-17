package com.elslode.onetwotrip.data.network

import com.google.gson.annotations.SerializedName

data class PriceDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("amount")
    val amount: Int
)