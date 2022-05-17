package com.elslode.onetwotrip.data.database

import com.google.gson.annotations.SerializedName

data class TripDbModel(
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String
)