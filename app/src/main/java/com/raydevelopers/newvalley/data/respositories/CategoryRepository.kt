package com.raydevelopers.newvalley.data.respositories

import com.raydevelopers.newvalley.data.localsource.CategoryLocalDataSource
import com.raydevelopers.newvalley.data.remote.CategoryRemoteDataSource
import com.raydevelopers.newvalley.data.remote.NewEpisodeRemoteDataSource
import com.raydevelopers.newvalley.models.category.Category
import com.raydevelopers.newvalley.utility.CATEGORY_URL

class CategoryRepository(var categoryRemoteDataSource: CategoryRemoteDataSource,
                        var categoryLocalDataSource: CategoryLocalDataSource) {

    suspend fun getCategories() = categoryRemoteDataSource.getCategories()
    suspend fun getCategoriesFromDatabase():Category? = categoryLocalDataSource.getCategories()
    suspend fun insertCategory(category: Category) = categoryLocalDataSource.insertCategories(category)
}