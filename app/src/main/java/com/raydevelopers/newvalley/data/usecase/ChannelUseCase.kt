package com.raydevelopers.newvalley.data.usecase

import androidx.lifecycle.LiveData
import com.raydevelopers.newvalley.network.NetworkResource

interface ChannelUseCase {
    fun getChannels(): LiveData<NetworkResource<Any>>
}