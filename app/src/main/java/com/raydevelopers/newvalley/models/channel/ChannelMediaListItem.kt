package com.raydevelopers.newvalley.models.channel

import com.raydevelopers.newvalley.models.ComponentViewType

class ChannelMediaListItem:ComponentViewType(CHANNEL_COURSE_VIEW_TYPE) {
    var coverAsset: String? = null
    var id: String? = null
    var title: String? = null
    var type: String? = null
}