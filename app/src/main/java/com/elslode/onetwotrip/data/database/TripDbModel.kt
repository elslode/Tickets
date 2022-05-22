package com.elslode.onetwotrip.data.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TripDbModel(
    val from: String,
    val to: String
): Parcelable