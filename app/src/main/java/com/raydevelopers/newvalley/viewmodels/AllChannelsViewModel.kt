package com.raydevelopers.newvalley.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.raydevelopers.newvalley.MindValleyApplication
import com.raydevelopers.newvalley.data.usecase.AllChannelMergeUseCase
import com.raydevelopers.newvalley.data.usecase.CategoryUseCase
import com.raydevelopers.newvalley.data.usecase.ChannelUseCase
import com.raydevelopers.newvalley.data.usecase.NewEpisodeUseCase
import com.raydevelopers.newvalley.models.ComponentViewType
import com.raydevelopers.newvalley.models.HeaderAdapterInfo
import com.raydevelopers.newvalley.models.category.Category
import com.raydevelopers.newvalley.models.category.Data
import com.raydevelopers.newvalley.models.channel.Channel
import com.raydevelopers.newvalley.models.newepisode.NewEpisode
import com.raydevelopers.newvalley.network.NetworkResource
import com.raydevelopers.newvalley.network.NetworkUtils
import com.raydevelopers.newvalley.utility.ERROR_OCCURRED
import com.raydevelopers.newvalley.utility.ERROR_OFFLINE
import com.raydevelopers.newvalley.utility.observeOnce
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllChannelsViewModel(
    private val categoryUseCase: CategoryUseCase,
    private val channelUseCase: ChannelUseCase,
    private val newEpisodeUseCase: NewEpisodeUseCase,
    private val allChannelMergeUseCase: AllChannelMergeUseCase
) : ViewModel() {
    // live data for storing the result for new episodes
    private var newEpisodeLiveData: LiveData<NetworkResource<NewEpisode?>> =
        MutableLiveData<NetworkResource<NewEpisode?>>()
    // live data for storing the result for channels
    private var channelLiveData: LiveData<NetworkResource<Channel?>> =
        MutableLiveData<NetworkResource<Channel?>>()
    var categoryModel:LiveData<NetworkResource<Category?>> = MutableLiveData<NetworkResource<Category?>>()
    // create the list of merged response
    var mergedResponseLiveData =
       MutableLiveData<HeaderAdapterInfo>()
    // error
    var errorLiveData = MutableLiveData<String>()

    private val mergerJob = CoroutineScope(Dispatchers.Default)

    /**
     * Call to hit the network request and get all the responses and process them
     * we will only show results when all responses comes back if even one got error
     * we will error out the screen
     */
    fun getAllChannels() {
        newEpisodeLiveData = newEpisodeUseCase.getNewEpisodes()
        channelLiveData = channelUseCase.getChannels()
        processCategoryResponse()
    }

    /**
     * process the response for new episode live data
     */
    private fun processNewEpisodes(category: Category) {
        newEpisodeLiveData?.observeOnce(Observer { networkResponse ->
            when (networkResponse.status) {
                NetworkResource.Status.SUCCESS -> {
                    networkResponse?.data?.let {newEpsode->
                        processChannels(category,newEpsode)
                    }
                }
                NetworkResource.Status.ERROR -> {
                    // show error  and cancel all jobs
                    errorLiveData.value = networkResponse.message?: ERROR_OCCURRED
                }
            }
        })
    }

    /**
     * process the response for new episode channel live data
     */
    private fun processChannels(category: Category?,newEpisode: NewEpisode?) {
        channelLiveData?.observeOnce(Observer { networkResponse ->
            when (networkResponse.status) {
                NetworkResource.Status.SUCCESS -> {
                    networkResponse?.data?.let {channel->
                        if(newEpisode!=null && channel!=null && category!=null)
                        {
                            executeMerging(newEpisode,channel,category)
                        }
                    }

                }
                NetworkResource.Status.ERROR -> {
                    // show error  and cancel all jobs
                    errorLiveData.value = networkResponse.message?: ERROR_OCCURRED

                }
            }
        })
    }

    /**
     * process the response for new episode category
     */
    private fun processCategoryResponse() {
        categoryModel = categoryUseCase.getCategories()
        categoryModel?.observeOnce(Observer { networkResponse ->
            when (networkResponse.status) {
                NetworkResource.Status.SUCCESS -> {

                    networkResponse.data?.let { category ->
                        processNewEpisodes(category)
                    }
                }
                NetworkResource.Status.ERROR -> {
                    // show error
                    errorLiveData.value = networkResponse.message?: ERROR_OCCURRED
                }
            }
        })
    }

    private fun executeMerging(newEpisode: NewEpisode, channel:Channel, category: Category) {
        mergerJob.launch {
            allChannelMergeUseCase.mergeCategories(
                mergedResponseLiveData,
                newEpisode,
                channel,
                category
            )
        }
    }
}
