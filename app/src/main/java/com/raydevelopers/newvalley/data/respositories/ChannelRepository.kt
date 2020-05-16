package com.raydevelopers.newvalley.data.respositories

import com.raydevelopers.newvalley.data.localsource.ChannelLocalDataSource
import com.raydevelopers.newvalley.data.remote.ChannelRemoteDataSource
import com.raydevelopers.newvalley.models.channel.Channel

class ChannelRepository(
    var channelRemoteDataSource: ChannelRemoteDataSource,
    var chanLocalDataSource: ChannelLocalDataSource
) {

    suspend fun getChannels() = channelRemoteDataSource.getChannels()
    suspend fun getChannelFromDatabase(): Channel? = chanLocalDataSource.getChannel()
    suspend fun insertChannel(channel: Channel) = chanLocalDataSource.insertChannel(channel)
}