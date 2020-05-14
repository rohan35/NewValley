package com.raydevelopers.newvalley.data.transformer

import androidx.lifecycle.liveData
import com.raydevelopers.newvalley.data.remote.CategoryRemoteDataSource
import com.raydevelopers.newvalley.data.usecase.CategoryUseCase
import com.raydevelopers.newvalley.network.NetworkResource
import kotlinx.coroutines.Dispatchers

class CategoryTransFormer(private val remoteDataSource: CategoryRemoteDataSource) :
    CategoryUseCase {
    override fun getCategories() = liveData(Dispatchers.IO) {
        emit(NetworkResource.loading(data = null))
        try {
            emit(
                NetworkResource.success(
                    data =
                    remoteDataSource.getCategories()
                )
            )
        } catch (exception: Exception) {
            emit(
                NetworkResource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred!"
                )
            )
        }

    }
}