package com.raydevelopers.newvalley.models.category

data class Category(
    val `data`: Data
)

data class Data(
    val categories: List<CategoryX>
)

data class CategoryX(
    val name: String
)