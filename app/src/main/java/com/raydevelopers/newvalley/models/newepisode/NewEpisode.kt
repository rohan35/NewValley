package com.raydevelopers.newvalley.models.newepisode

data class NewEpisode(
    val `data`: Data
)

data class Data(
    val media: List<Media>
)

data class Media(
    val channel: Channel,
    val coverAsset: CoverAsset,
    val title: String,
    val type: String
)

data class Channel(
    val title: String
)

data class CoverAsset(
    val url: String
)