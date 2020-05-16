package com.raydevelopers.newvalley.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raydevelopers.newvalley.models.category.Category

@Dao
interface CategoryDao {

    @Query("SELECT * from Category")
    suspend fun getCategories(): Category?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)


    @Query("DELETE FROM Category")
    suspend fun deleteAll()
}