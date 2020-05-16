package com.raydevelopers.newvalley.data.localsource

import com.raydevelopers.newvalley.database.ChannelDao
import com.raydevelopers.newvalley.models.channel.Channel

class ChannelLocalDataSource(var channelDao: ChannelDao) {
    suspend fun getChannel():Channel? = channelDao.getChannels()
    suspend fun insertChannel(channel: Channel) = channelDao.insert(channel)
}