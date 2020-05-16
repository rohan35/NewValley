package com.raydevelopers.newvalley.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raydevelopers.newvalley.models.newepisode.NewEpisode

@Dao
interface NewEpisodeDao {
    @Query("SELECT * from NewEpisode")
    suspend fun getNewEpisode(): NewEpisode?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(newEpisode: NewEpisode)


    @Query("DELETE FROM NewEpisode")
    suspend fun deleteAll()
}