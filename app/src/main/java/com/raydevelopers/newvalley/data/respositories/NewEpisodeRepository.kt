package com.raydevelopers.newvalley.data.respositories

import com.raydevelopers.newvalley.data.localsource.NewEpisodeLocalDataSource
import com.raydevelopers.newvalley.data.remote.NewEpisodeRemoteDataSource
import com.raydevelopers.newvalley.models.newepisode.NewEpisode

class NewEpisodeRepository(
    var newEpisodeRemoteDataSource: NewEpisodeRemoteDataSource,
    var newEpisodeLocalDataSource: NewEpisodeLocalDataSource
) {
    suspend fun getNewEpisodes() = newEpisodeRemoteDataSource.getNewEpisodes()
    suspend fun getNewEpisodeFromDatabase(): NewEpisode? =
        newEpisodeLocalDataSource.getNewEpisodes()

    suspend fun insertNewEpisode(newEpisode: NewEpisode) =
        newEpisodeLocalDataSource.insertNewEpisode(newEpisode)
}