package com.raydevelopers.newvalley.models.category

import com.raydevelopers.newvalley.models.ComponentViewType

data class Category(
    val data: Data?
)

data class Data(
    val categories: List<CategoryX?>?
)

data class CategoryX(
    val name: String?
):ComponentViewType(CATEGORY_VIEW_TYPE)