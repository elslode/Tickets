package com.elslode.onetwotrip.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TicketsDao {
    @Query("SELECT * FROM trips_table")
    fun getTicketList(): LiveData<List<TicketEntityDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTicketsList(trips: List<TicketEntityDbModel>)

    @Query("SELECT * FROM trips_table WHERE id=:id LIMIT 1")
    suspend fun getTripItem(id: Int): TicketEntityDbModel

    @Query("SELECT id FROM trips_table WHERE id = :id LIMIT 1")
    fun getItemId(id: Int): Int
}