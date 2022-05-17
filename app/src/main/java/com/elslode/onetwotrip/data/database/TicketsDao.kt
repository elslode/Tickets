package com.elslode.onetwotrip.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface TicketsDao {
    @Query("SELECT * FROM trips_table")
    fun getTicketList(): LiveData<List<TripsResponseDbModel>>

    @Insert(onConflict = REPLACE)
    fun saveTicketsList(trips: TripsResponseDbModel)

    @Query("SELECT * FROM trips_table WHERE id=:id LIMIT 1")
    suspend fun getTripItem(id: Int): TripsResponseDbModel

    @Query("SELECT id FROM trips_table WHERE id = :id LIMIT 1")
    fun getItemId(id: Int): Int
}