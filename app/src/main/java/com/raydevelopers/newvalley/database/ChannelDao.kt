package com.raydevelopers.newvalley.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raydevelopers.newvalley.models.channel.Channel

@Dao
interface ChannelDao {
    @Query("SELECT * from Channel")
    suspend fun getChannels(): Channel?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(channel: Channel)


    @Query("DELETE FROM Channel")
    suspend fun deleteAll()
}