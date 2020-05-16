package com.raydevelopers.newvalley.data.localsource

import com.raydevelopers.newvalley.database.NewEpisodeDao
import com.raydevelopers.newvalley.models.newepisode.NewEpisode

class NewEpisodeLocalDataSource(var newEpisodeDao: NewEpisodeDao) {
    suspend fun getNewEpisodes():NewEpisode? = newEpisodeDao.getNewEpisode()
    suspend fun insertNewEpisode(newEpisode: NewEpisode) = newEpisodeDao.insert(newEpisode)
}