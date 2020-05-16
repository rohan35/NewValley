package com.raydevelopers.newvalley.data.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raydevelopers.newvalley.models.ComponentViewType
import com.raydevelopers.newvalley.models.HeaderAdapterInfo
import com.raydevelopers.newvalley.models.category.Category
import com.raydevelopers.newvalley.models.channel.Channel
import com.raydevelopers.newvalley.models.newepisode.Data
import com.raydevelopers.newvalley.models.newepisode.NewEpisode

interface AllChannelMergeUseCase {
    suspend fun mergeCategories(
        mergedResponseLiveData: MutableLiveData<HeaderAdapterInfo>,
        newEpisode: NewEpisode,
        channel: Channel,
        category: Category
    )
}