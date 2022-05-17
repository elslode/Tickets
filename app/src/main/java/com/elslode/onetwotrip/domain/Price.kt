package com.elslode.onetwotrip.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Price(
    @SerializedName("type")
    val type: String,
    @SerializedName("amount")
    val amount: Int
): Parcelable