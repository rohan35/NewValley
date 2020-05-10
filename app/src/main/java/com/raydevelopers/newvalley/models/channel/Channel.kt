package com.raydevelopers.newvalley.models.channel

data class Channel(
    val `data`: Data
)

data class Data(
    val channels: List<ChannelX>
)

data class ChannelX(
    val coverAsset: CoverAsset,
    val iconAsset: IconAsset,
    val id: String,
    val latestMedia: List<LatestMedia>,
    val mediaCount: Int,
    val series: List<Sery>,
    val slug: String,
    val title: String
)

data class CoverAsset(
    val url: String
)

data class IconAsset(
    val thumbnailUrl: String,
    val url: String
)

data class LatestMedia(
    val coverAsset: CoverAssetX,
    val title: String,
    val type: String
)

data class Sery(
    val coverAsset: CoverAssetXX,
    val id: String,
    val title: String
)

data class CoverAssetX(
    val url: String
)

data class CoverAssetXX(
    val url: String
)