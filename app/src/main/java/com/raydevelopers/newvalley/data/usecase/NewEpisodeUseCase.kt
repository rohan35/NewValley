package com.raydevelopers.newvalley.data.usecase

import androidx.lifecycle.LiveData
import com.raydevelopers.newvalley.network.NetworkResource

interface NewEpisodeUseCase {
    fun getNewEpisodes(): LiveData<NetworkResource<Any>>
}