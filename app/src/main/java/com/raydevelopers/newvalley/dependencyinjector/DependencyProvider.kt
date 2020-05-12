package com.raydevelopers.newvalley.dependencyinjector

import com.raydevelopers.newvalley.data.remote.CategoryRemoteDataSource
import com.raydevelopers.newvalley.data.remote.ChannelRemoteDataSource
import com.raydevelopers.newvalley.data.remote.NewEpisodeRemoteDataSource
import com.raydevelopers.newvalley.data.transformer.CategoryTransFormer
import com.raydevelopers.newvalley.data.transformer.ChannelTransformer
import com.raydevelopers.newvalley.data.transformer.NewEpisodeTransFormer
import com.raydevelopers.newvalley.data.usecase.CategoryUseCase
import com.raydevelopers.newvalley.data.usecase.ChannelUseCase
import com.raydevelopers.newvalley.data.usecase.NewEpisodeUseCase
import com.raydevelopers.newvalley.factories.AllChannelsViewModelFactory

object DependencyProvider {
    /**
     * provide the instance of view model from viewModelFactory.newInstance
     * @return object of allChannelViewModel
     */
    fun provideLoginViewModelFactory(): AllChannelsViewModelFactory {
        // create the use cases
        val categoryUseCase = getCategoryUseCase()
        val channelUseCase = getChannelUseCase()
        val newEpisodeUseCase = getNewEpisodeUseCase()
        // pass the use cases to constructor of viewModel through viewModelFactory
        return AllChannelsViewModelFactory(categoryUseCase, channelUseCase, newEpisodeUseCase)
    }

    /**
     * provide the instance of category Transformer which implements category use case
     * @return object of [CategoryTransFormer]
     */
    private fun getCategoryUseCase(): CategoryUseCase {
        return CategoryTransFormer(getCategoryRemoteDataSource())
    }

    /**
     * provide the instance of categoryRemoteDataSource
     * @return object of [CategoryRemoteDataSource]
     */
    private fun getCategoryRemoteDataSource(): CategoryRemoteDataSource {
        return CategoryRemoteDataSource()
    }

    /**
     * provide the instance of channelTransfomer which implements [ChannelUseCase]
     * @return object of [ChannelTransformer]
     */
    private fun getChannelUseCase(): ChannelUseCase {
        return ChannelTransformer(getChannelRemoteDataSource())
    }

    /**
     * provide the instance of ChannelRemoteDataSource
     * @return object of [ChannelRemoteDataSource]
     */
    private fun getChannelRemoteDataSource(): ChannelRemoteDataSource {
        return ChannelRemoteDataSource()
    }

    /**
     * provide the instance of newEpisodeTransformer
     * @return object of [NewEpisodeTransFormer]
     */
    private fun getNewEpisodeUseCase(): NewEpisodeUseCase {
        return NewEpisodeTransFormer(getNewEpisodeRemoteDataSource())
    }

    /**
     * provide the instance of newEpisodeRemoteDataSource
     * @return object of [NewEpisodeRemoteDataSource]
     */
    private fun getNewEpisodeRemoteDataSource(): NewEpisodeRemoteDataSource {
        return NewEpisodeRemoteDataSource()
    }

}