package com.raydevelopers.newvalley.models.channel

import com.raydevelopers.newvalley.models.ComponentViewType

data class Channel(
    val data: Data?
)

data class Data(
    val channels: List<ChannelX?>?
)

data class ChannelX(
    val coverAsset: CoverAsset? = null,
    val iconAsset: IconAsset? = null,
    val id: String? = null,
    val latestMedia: List<LatestMedia?>? = null,
    val mediaCount: Int? = null,
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