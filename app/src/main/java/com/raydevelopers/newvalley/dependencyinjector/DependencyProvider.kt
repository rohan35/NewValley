package com.raydevelopers.newvalley.dependencyinjector

import com.raydevelopers.newvalley.MindValleyApplication
import com.raydevelopers.newvalley.data.localsource.CategoryLocalDataSource
import com.raydevelopers.newvalley.data.localsource.ChannelLocalDataSource
import com.raydevelopers.newvalley.data.localsource.NewEpisodeLocalDataSource
import com.raydevelopers.newvalley.data.remote.CategoryRemoteDataSource
import com.raydevelopers.newvalley.data.remote.ChannelRemoteDataSource
import com.raydevelopers.newvalley.data.remote.NewEpisodeRemoteDataSource
import com.raydevelopers.newvalley.data.respositories.AppRepository
import com.raydevelopers.newvalley.data.respositories.CategoryRepository
import com.raydevelopers.newvalley.data.respositories.ChannelRepository
import com.raydevelopers.newvalley.data.respositories.NewEpisodeRepository
import com.raydevelopers.newvalley.data.transformer.AllChannelsMergeTransformer
import com.raydevelopers.newvalley.data.transformer.CategoryTransFormer
import com.raydevelopers.newvalley.data.transformer.ChannelTransformer
import com.raydevelopers.newvalley.data.transformer.NewEpisodeTransFormer
import com.raydevelopers.newvalley.data.usecase.AllChannelMergeUseCase
import com.raydevelopers.newvalley.data.usecase.CategoryUseCase
import com.raydevelopers.newvalley.data.usecase.ChannelUseCase
import com.raydevelopers.newvalley.data.usecase.NewEpisodeUseCase
import com.raydevelopers.newvalley.database.AppDatabase
import com.raydevelopers.newvalley.database.CategoryDao
import com.raydevelopers.newvalley.database.ChannelDao
import com.raydevelopers.newvalley.database.NewEpisodeDao
import com.raydevelopers.newvalley.factories.AllChannelsViewModelFactory
import com.raydevelopers.newvalley.network.RetrofitService
import com.raydevelopers.newvalley.network.ServiceHelper

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
        val allChannelsMergeUseCase = getAllChannelsMergeUseCase()
        // pass the use cases to constructor of viewModel through viewModelFactory
        return AllChannelsViewModelFactory(categoryUseCase, channelUseCase, newEpisodeUseCase,allChannelsMergeUseCase)
    }

    /**
     * provide the instance of category Transformer which implements category use case
     * @return object of [CategoryTransFormer]
     */
    private fun getCategoryUseCase(): CategoryUseCase {
        return CategoryTransFormer(getCategoryRepository())
    }

    private fun getCategoryRepository(): CategoryRepository {
        return CategoryRepository(getCategoryRemoteDataSource(), getCategoryLocalDataSource())
    }

    private fun getCategoryLocalDataSource(): CategoryLocalDataSource {
        return CategoryLocalDataSource(getCategoryDao())
    }

    private fun getCategoryDao(): CategoryDao {
        return AppDatabase.getDatabase(MindValleyApplication.applicationContext()).categoryDao()
    }

    /**
     * provide the instance of categoryRemoteDataSource
     * @return object of [CategoryRemoteDataSource]
     */
    private fun getCategoryRemoteDataSource(): CategoryRemoteDataSource {
        return CategoryRemoteDataSource(getRepository())
    }

    /**
     * provide the instance of channelTransfomer which implements [ChannelUseCase]
     * @return object of [ChannelTransformer]
     */
    private fun getChannelUseCase(): ChannelUseCase {
        return ChannelTransformer(getChannelRepository())
    }
    private fun  getChannelRepository():ChannelRepository
    {
        return ChannelRepository(getChannelRemoteDataSource(), getChannelLocalDataSource())
    }
    private fun getChannelLocalDataSource():ChannelLocalDataSource
    {
        return ChannelLocalDataSource(getChannelDao())
    }

    private fun getChannelDao():ChannelDao
    {
        return AppDatabase.getDatabase(MindValleyApplication.applicationContext()).getChannelDao()
    }

    /**
     * provide the instance of ChannelRemoteDataSource
     * @return object of [ChannelRemoteDataSource]
     */
    private fun getChannelRemoteDataSource(): ChannelRemoteDataSource {
        return ChannelRemoteDataSource(getRepository())
    }

    /**
     * provide the instance of newEpisodeTransformer
     * @return object of [NewEpisodeTransFormer]
     */
    private fun getNewEpisodeUseCase(): NewEpisodeUseCase {
        return NewEpisodeTransFormer(getNewEpisodeRepository())
    }
    private fun getNewEpisodeRepository():NewEpisodeRepository{
        return NewEpisodeRepository(getNewEpisodeRemoteDataSource(), getNewEpisodeLocalDataSource())
    }
    private fun getNewEpisodeLocalDataSource():NewEpisodeLocalDataSource
    {
        return NewEpisodeLocalDataSource(getNewEpisodeDao())
    }

    private fun getNewEpisodeDao():NewEpisodeDao
    {
        return AppDatabase.getDatabase(MindValleyApplication.applicationContext()).getNewEpisodeDao()
    }
    /**
     * provide the instance of newEpisodeRemoteDataSource
     * @return object of [NewEpisodeRemoteDataSource]
     */
    private fun getNewEpisodeRemoteDataSource(): NewEpisodeRemoteDataSource {
        return NewEpisodeRemoteDataSource(getRepository())
    }

    private fun getRepository(): AppRepository {
        return AppRepository(getServiceHelper())
    }

    private fun getServiceHelper(): ServiceHelper {
        return ServiceHelper(RetrofitService.networkService)
    }
    /**
     * provide the instance of category Transformer which implements category use case
     * @return object of [CategoryTransFormer]
     */
    private fun getAllChannelsMergeUseCase(): AllChannelMergeUseCase {
        return AllChannelsMergeTransformer()
    }

}