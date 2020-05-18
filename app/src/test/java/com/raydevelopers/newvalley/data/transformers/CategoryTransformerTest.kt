package com.raydevelopers.newvalley.data.transformers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raydevelopers.newvalley.data.respositories.CategoryRepository
import com.raydevelopers.newvalley.data.transformer.CategoryTransFormer
import com.raydevelopers.newvalley.models.category.Category
import com.raydevelopers.newvalley.network.NetworkResource
import com.raydevelopers.newvalley.utility.MainCoroutineScopeRule
import junit.framework.Assert.assertEquals
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
    CategoryRepository::class
)
class CategoryTransformerTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()
    private val categoryRepository = Mockito.mock(CategoryRepository::class.java)
    private lateinit var categoryTransformer: CategoryTransFormer

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.categoryTransformer = CategoryTransFormer(categoryRepository)
    }

    private fun getCategorySuccessLiveData(): LiveData<NetworkResource<Category?>> {
        val response = MutableLiveData<NetworkResource<Category?>>()
        response.value?.status = NetworkResource.Status.SUCCESS
        return response
    }

    private fun getCategoryErrorLiveData(): LiveData<NetworkResource<Category?>> {
        val response = MutableLiveData<NetworkResource<Category?>>()
        response.value?.status = NetworkResource.Status.ERROR
        return response
    }

    @Test
    fun get_error_categroires() {
        var status_mocked = NetworkResource.Status.ERROR
        var statusActual: NetworkResource.Status? = null
        val lock = CountDownLatch(1)
        val liveData = categoryTransformer.getCategories()
        liveData?.observeForever {
            statusActual = it.status
            lock.countDown()
        }
        lock.await()
        assertEquals(status_mocked, statusActual)
    }
}
