package com.raydevelopers.newvalley.viewmodels

import androidx.lifecycle.ViewModel
import com.raydevelopers.newvalley.data.usecase.CategoryUseCase
import com.raydevelopers.newvalley.data.usecase.ChannelUseCase
import com.raydevelopers.newvalley.data.usecase.NewEpisodeUseCase

class AllChannelsViewModel(private val categoryUseCase: CategoryUseCase,
                           private val channelUseCase: ChannelUseCase,
                           private val newEpisodeUseCase: NewEpisodeUseCase
) : ViewModel() {

}
