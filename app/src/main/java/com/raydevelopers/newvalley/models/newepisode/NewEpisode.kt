package com.raydevelopers.newvalley.models.newepisode

import com.raydevelopers.newvalley.models.ComponentViewType

data class NewEpisode(
    val data: Data?
)

data class Data(
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