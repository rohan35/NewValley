package com.raydevelopers.newvalley.models.category

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.raydevelopers.newvalley.models.ComponentViewType
import com.raydevelopers.newvalley.utility.RoomTypeConverters

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @Embedded
    val data: Data?= null
)

data class Data(
    @TypeConverters(RoomTypeConverters::class)
    val categories: List<CategoryX?>? = null
)

data class CategoryX(
    val name: String? = null
):ComponentViewType(CATEGORY_VIEW_TYPE)