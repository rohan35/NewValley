package com.raydevelopers.newvalley.data.usecase

import androidx.lifecycle.LiveData
import com.raydevelopers.newvalley.models.category.Category
import com.raydevelopers.newvalley.network.NetworkResource

interface CategoryUseCase {
    fun getCategories(): LiveData<NetworkResource<Category?>>
}