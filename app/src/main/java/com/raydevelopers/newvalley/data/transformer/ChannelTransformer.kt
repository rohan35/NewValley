package com.raydevelopers.newvalley.data.transformer

import androidx.lifecycle.liveData
import com.raydevelopers.newvalley.data.remote.ChannelRemoteDataSource
import com.raydevelopers.newvalley.data.usecase.ChannelUseCase
import com.raydevelopers.newvalley.network.NetworkResource
import kotlinx.coroutines.Dispatchers

class ChannelTransformer(private val channelRemoteDataSource: ChannelRemoteDataSource) :
    ChannelUseCase {
    override fun getChannels() =
        liveData(Dispatchers.IO) {
            emit(NetworkResource.loading(data = null))
            try {
                emit(
                    NetworkResource.success(
                        data =
                        channelRemoteDataSource.getChannels()
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