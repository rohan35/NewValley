package com.raydevelopers.newvalley.data.remote

import com.raydevelopers.newvalley.data.respositories.AppRepository
import com.raydevelopers.newvalley.utility.CATEGORY_URL

class CategoryRemoteDataSource(private var appRepository: AppRepository) {
    suspend fun getCategories() = appRepository.getCall(CATEGORY_URL)
}