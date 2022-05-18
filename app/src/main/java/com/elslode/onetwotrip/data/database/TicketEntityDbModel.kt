package com.elslode.onetwotrip.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "trips_table")
@Parcelize
data class TicketEntityDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val currency: String,
    val prices: List<PriceDbModel>,
    val trips: List<TripDbModel>
): Parcelable