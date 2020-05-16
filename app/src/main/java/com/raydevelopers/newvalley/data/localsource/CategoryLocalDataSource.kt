package com.raydevelopers.newvalley.data.localsource

import com.raydevelopers.newvalley.database.CategoryDao
import com.raydevelopers.newvalley.models.category.Category

class CategoryLocalDataSource(private var categoryDao: CategoryDao) {

    suspend fun getCategories():Category? = categoryDao.getCategories()
    suspend fun insertCategories(category:Category) = categoryDao.insert(category)
}