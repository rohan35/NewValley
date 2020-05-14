package com.raydevelopers.newvalley.data.remote

import androidx.lifecycle.LiveData
import com.raydevelopers.newvalley.data.respositories.AppRepository
import com.raydevelopers.newvalley.models.channel.Channel
import com.raydevelopers.newvalley.utility.CHANNEL_URL

class ChannelRemoteDataSource(private val appRepository: AppRepository) {
   suspend fun getChannels() = appRepository.getCall(CHANNEL_URL)
}