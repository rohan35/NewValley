package com.raydevelopers.newvalley.data.transformer

import androidx.lifecycle.liveData
import com.raydevelopers.newvalley.data.remote.NewEpisodeRemoteDataSource
import com.raydevelopers.newvalley.data.usecase.NewEpisodeUseCase
import com.raydevelopers.newvalley.network.NetworkResource
import kotlinx.coroutines.Dispatchers

class NewEpisodeTransFormer(private val newEpisodeRemoteDataSource: NewEpisodeRemoteDataSource) :
    NewEpisodeUseCase {
    override fun getNewEpisodes() =
        liveData(Dispatchers.IO) {
            emit(NetworkResource.loading(data = null))
            try {
                emit(
                    NetworkResource.success(
                        data =
                        newEpisodeRemoteDataSource.getNewEpisodes()
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