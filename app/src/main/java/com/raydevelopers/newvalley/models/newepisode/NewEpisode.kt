package com.raydevelopers.newvalley.models.newepisode

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.raydevelopers.newvalley.models.ComponentViewType
import com.raydevelopers.newvalley.utility.RoomTypeConverters

@Entity
data class NewEpisode(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @Embedded(prefix = "_data_")
    val data: Data?
)

data class Data(
    @TypeConverters(RoomTypeConverters::class)
    val media: List<Media?>?
)

data class Media(
    val channel: Channel? = null,
    val coverAsset: CoverAsset? = null,
    val title: String? = null,
    val type: String? = null
):ComponentViewType(NEW_EPISODE_ITEM_VIEW_TYPE)

data class Channel(
    val title: String?
)

data class CoverAsset(
    val url: String?
)