package com.raydevelopers.newvalley.data.transformers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raydevelopers.newvalley.data.respositories.ChannelRepository
import com.raydevelopers.newvalley.data.respositories.NewEpisodeRepository
import com.raydevelopers.newvalley.data.transformer.NewEpisodeTransFormer
import com.raydevelopers.newvalley.models.newepisode.NewEpisode
import com.raydevelopers.newvalley.network.NetworkResource
import com.raydevelopers.newvalley.utility.MainCoroutineScopeRule
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import java.util.concurrent.CountDownLatch


@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(MockitoJUnitRunner::class)
@PrepareForTest(
    NewEpisodeRepository::class
)
class NewEpsiodeTransformerTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val newRepository = Mockito.mock(NewEpisodeRepository::class.java)
    private lateinit var newEpisodeTransformer: NewEpisodeTransFormer

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.newEpisodeTransformer = NewEpisodeTransFormer(newRepository)
    }

    private fun getNewEpisodeSuccessLiveData(): LiveData<NetworkResource<NewEpisode?>> {
        val response = MutableLiveData<NetworkResource<NewEpisode?>>()
        response.value?.status = NetworkResource.Status.SUCCESS
        return response
    }

    private fun getNewEpisodeErrorLiveData(): LiveData<NetworkResource<NewEpisode?>> {
        val response = MutableLiveData<NetworkResource<NewEpisode?>>()
        response.value?.status = NetworkResource.Status.ERROR
        return response
    }

    @Test
    fun get_error_epsiodes() {
        var status_mocked = NetworkResource.Status.ERROR
        var statusActual: NetworkResource.Status? = null
        val lock = CountDownLatch(1)
        val liveData = newEpisodeTransformer.getNewEpisodes()
        liveData?.observeForever {
            statusActual = it.status
            lock.countDown()
        }
        lock.await()
        Assert.assertEquals(status_mocked, statusActual)
    }




}