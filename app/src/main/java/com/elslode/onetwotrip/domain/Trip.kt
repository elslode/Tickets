package com.elslode.onetwotrip.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trip(
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String
): Parcelable