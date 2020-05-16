package com.raydevelopers.newvalley.data.transformer

import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.raydevelopers.newvalley.data.respositories.CategoryRepository
import com.raydevelopers.newvalley.data.usecase.CategoryUseCase
import com.raydevelopers.newvalley.models.category.Category
import com.raydevelopers.newvalley.network.NetworkResource
import com.raydevelopers.newvalley.network.NetworkUtils
import kotlinx.coroutines.Dispatchers

class CategoryTransFormer(private val categoryRepository: CategoryRepository) :
    CategoryUseCase {
    override fun getCategories() = liveData(Dispatchers.IO) {
        try {
            val remoteResponse = NetworkUtils.getModelFromJsonString(
                (Gson().toJsonTree
                    (categoryRepository.getCategories())).asJsonObject.toString(),
                Category::class.java
            )
            if (remoteResponse != null)
            {
                categoryRepository.insertCategory(remoteResponse)
            }


            emit(
                NetworkResource.success(
                    data = categoryRepository.getCategoriesFromDatabase()

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