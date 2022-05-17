package com.elslode.onetwotrip.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converter {

    var gson = Gson()

    @TypeConverter
    fun stringToPriceList(data: String?): List<PriceDbModel?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<PriceDbModel?>?>() {}.type
        return gson.fromJson<List<PriceDbModel?>>(data, listType)
    }

    @TypeConverter
    fun PriceListToString(someObjects: List<PriceDbModel?>?): String? {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToTripList(data: String?): List<TripDbModel?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<TripDbModel?>?>() {}.type
        return gson.fromJson<List<TripDbModel?>>(data, listType)
    }

    @TypeConverter
    fun TripListToString(someObjects: List<TripDbModel?>?): String? {
        return gson.toJson(someObjects)
    }
}