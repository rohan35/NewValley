package com.raydevelopers.newvalley.data.transformer

import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.raydevelopers.newvalley.data.remote.ChannelRemoteDataSource
import com.raydevelopers.newvalley.data.usecase.ChannelUseCase
import com.raydevelopers.newvalley.models.channel.Channel
import com.raydevelopers.newvalley.network.NetworkResource
import com.raydevelopers.newvalley.network.NetworkUtils
import kotlinx.coroutines.Dispatchers

class ChannelTransformer(private val channelRemoteDataSource: ChannelRemoteDataSource) :
    ChannelUseCase {
    override fun getChannels() =
        liveData(Dispatchers.IO) {
            try {
                emit(
                    NetworkResource.success(
                        data =
                        NetworkUtils.getModelFromJsonString((Gson().toJsonTree
                            (channelRemoteDataSource.getChannels())).asJsonObject.toString()
                            ,Channel::class.java)
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