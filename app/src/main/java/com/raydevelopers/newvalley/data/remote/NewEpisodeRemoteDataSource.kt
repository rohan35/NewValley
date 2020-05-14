package com.raydevelopers.newvalley.data.remote

import androidx.lifecycle.LiveData
import com.raydevelopers.newvalley.data.respositories.AppRepository
import com.raydevelopers.newvalley.models.newepisode.NewEpisode
import com.raydevelopers.newvalley.utility.NEW_EPISODES_URL

class NewEpisodeRemoteDataSource(private var appRepository: AppRepository) {
  suspend  fun getNewEpisodes() = appRepository.getCall(NEW_EPISODES_URL)
}