package com.raydevelopers.newvalley.viewmodels

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.raydevelopers.newvalley.data.usecase.CategoryUseCase
import com.raydevelopers.newvalley.data.usecase.ChannelUseCase
import com.raydevelopers.newvalley.data.usecase.NewEpisodeUseCase
import com.raydevelopers.newvalley.utility.observeOnce

class AllChannelsViewModel(private val categoryUseCase: CategoryUseCase,
                           private val channelUseCase: ChannelUseCase,
                           private val newEpisodeUseCase: NewEpisodeUseCase
) : ViewModel() {
    fun getAllChannels()
    {
        newEpisodeUseCase.getNewEpisodes().observeOnce(Observer {

        })
        channelUseCase.getChannels()
        categoryUseCase.getCategories()

    }

}
