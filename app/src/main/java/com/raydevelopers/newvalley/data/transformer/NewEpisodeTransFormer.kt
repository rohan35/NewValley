package com.raydevelopers.newvalley.data.transformer

import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.raydevelopers.newvalley.data.remote.NewEpisodeRemoteDataSource
import com.raydevelopers.newvalley.data.usecase.NewEpisodeUseCase
import com.raydevelopers.newvalley.models.newepisode.NewEpisode
import com.raydevelopers.newvalley.network.NetworkResource
import com.raydevelopers.newvalley.network.NetworkUtils
import kotlinx.coroutines.Dispatchers

class NewEpisodeTransFormer(private val newEpisodeRemoteDataSource: NewEpisodeRemoteDataSource) :
    NewEpisodeUseCase {
    override fun getNewEpisodes() =
        liveData(Dispatchers.IO) {
            try {
                emit(
                    NetworkResource.success(
                        data =
                        NetworkUtils.getModelFromJsonString((Gson().toJsonTree
                            (newEpisodeRemoteDataSource.getNewEpisodes())).asJsonObject.toString(),
                            NewEpisode::class.java)
                    )
                )
            } catch (exception: Exception) {
                emit(
                    NetworkResource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )
                )
            }

        }
}