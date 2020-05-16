package com.raydevelopers.newvalley.data.transformer

import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.raydevelopers.newvalley.data.respositories.NewEpisodeRepository
import com.raydevelopers.newvalley.data.usecase.NewEpisodeUseCase
import com.raydevelopers.newvalley.models.newepisode.NewEpisode
import com.raydevelopers.newvalley.network.NetworkResource
import com.raydevelopers.newvalley.network.NetworkUtils
import kotlinx.coroutines.Dispatchers

class NewEpisodeTransFormer(private val newEpisodeRepository: NewEpisodeRepository) :
    NewEpisodeUseCase {
    override fun getNewEpisodes() =
        liveData(Dispatchers.IO) {
            try {
                val response =
                    NetworkUtils.getModelFromJsonString(
                        (Gson().toJsonTree
                            (newEpisodeRepository.getNewEpisodes())).asJsonObject.toString(),
                        NewEpisode::class.java
                    )
                if (response != null) {
                    newEpisodeRepository.insertNewEpisode(response)
                }
                emit(
                    NetworkResource.success(
                        data = newEpisodeRepository.getNewEpisodeFromDatabase()
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