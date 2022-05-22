package com.elslode.onetwotrip.data.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PriceDbModel(
    val type: String,
    val amount: Int
): Parcelable