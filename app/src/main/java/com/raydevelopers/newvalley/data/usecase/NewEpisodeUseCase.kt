package com.raydevelopers.newvalley.data.usecase

import androidx.lifecycle.LiveData
import com.raydevelopers.newvalley.models.newepisode.NewEpisode
import com.raydevelopers.newvalley.network.NetworkResource

interface NewEpisodeUseCase {
    fun getNewEpisodes(): LiveData<NetworkResource<NewEpisode?>>
}