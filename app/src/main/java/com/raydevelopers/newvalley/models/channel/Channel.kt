package com.raydevelopers.newvalley.models.channel

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.raydevelopers.newvalley.models.ComponentViewType
import com.raydevelopers.newvalley.utility.RoomTypeConverters

@Entity
data class Channel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @Embedded(prefix = "_data_")
    val data: Data?
)

data class Data(
    @TypeConverters(RoomTypeConverters::class)
    val channels: List<ChannelX?>?
)

data class ChannelX(
    @Embedded(prefix = "_cover_asset_")
    val coverAsset: CoverAsset? = null,
    @Embedded(prefix = "_icon_asset_")
    val iconAsset: IconAsset? = null,
    val id: String? = null,
    @TypeConverters(RoomTypeConverters::class)
    val latestMedia: List<LatestMedia?>? = null,
    val mediaCount: Int? = null,
    @TypeConverters(RoomTypeConverters::class)
    val series: List<Sery?>? = null,
    val slug: String? = null,
    val title: String? = null
):ComponentViewType(CHANNEL_SECTION_HEADER)
{
    var startIndex:Int = 0
    var endIndex:Int = 0
}

data class CoverAsset(
    val url: String?
)

data class IconAsset(
    val thumbnailUrl: String?,
    val url: String?
)

data class LatestMedia(
    val coverAsset: CoverAssetX?,
    val title: String?,
    val type: String?
)

data class Sery(
    val coverAsset: CoverAssetXX?,
    val id: String?,
    val title: String?
)

data class CoverAssetX(
    val url: String?
)

data class CoverAssetXX(
    val url: String?
)