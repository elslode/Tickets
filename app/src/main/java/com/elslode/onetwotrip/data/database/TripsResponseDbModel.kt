package com.elslode.onetwotrip.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "trips_table")
data class TripsResponseDbModel(
    @PrimaryKey
    var id: Int = UNDEFINED_ID,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("prices")

    val prices: List<PriceDbModel>,
    @SerializedName("trips")
    val trips: List<TripDbModel>
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}