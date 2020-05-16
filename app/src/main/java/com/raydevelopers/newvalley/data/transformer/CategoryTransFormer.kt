package com.raydevelopers.newvalley.data.transformer

import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.raydevelopers.newvalley.data.remote.CategoryRemoteDataSource
import com.raydevelopers.newvalley.data.usecase.CategoryUseCase
import com.raydevelopers.newvalley.models.category.Category
import com.raydevelopers.newvalley.network.NetworkResource
import com.raydevelopers.newvalley.network.NetworkUtils
import kotlinx.coroutines.Dispatchers

class CategoryTransFormer(private val remoteDataSource: CategoryRemoteDataSource) :
    CategoryUseCase {
    override fun getCategories() = liveData(Dispatchers.IO) {
        try {
            emit(
                NetworkResource.success(
                    data =
                    NetworkUtils.getModelFromJsonString((Gson().toJsonTree
                        (remoteDataSource.getCategories())).asJsonObject.toString(),Category::class.java)
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