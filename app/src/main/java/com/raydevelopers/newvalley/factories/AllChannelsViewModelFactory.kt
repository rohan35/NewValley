package com.raydevelopers.newvalley.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raydevelopers.newvalley.data.usecase.AllChannelMergeUseCase
import com.raydevelopers.newvalley.data.usecase.CategoryUseCase
import com.raydevelopers.newvalley.data.usecase.ChannelUseCase
import com.raydevelopers.newvalley.data.usecase.NewEpisodeUseCase
import com.raydevelopers.newvalley.viewmodels.AllChannelsViewModel

class AllChannelsViewModelFactory(
    private val categoryUseCase: CategoryUseCase,
    private val channelUseCase: ChannelUseCase,
    private val newEpisodeUseCase: NewEpisodeUseCase,
    private val allChannelMergeUseCase: AllChannelMergeUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllChannelsViewModel(categoryUseCase, channelUseCase, newEpisodeUseCase,allChannelMergeUseCase) as T
    }
}