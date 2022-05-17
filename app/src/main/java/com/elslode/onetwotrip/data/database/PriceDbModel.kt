package com.elslode.onetwotrip.data.database

import com.google.gson.annotations.SerializedName

data class PriceDbModel(
    @SerializedName("type")
    val type: String,
    @SerializedName("amount")
    val amount: Int
)