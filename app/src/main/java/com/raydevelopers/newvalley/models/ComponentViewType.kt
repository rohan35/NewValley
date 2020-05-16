package com.raydevelopers.newvalley.models

import com.raydevelopers.newvalley.models.category.CategoryX
import com.raydevelopers.newvalley.models.channel.ChannelX
import com.raydevelopers.newvalley.models.channel.LatestMedia
import com.raydevelopers.newvalley.models.channel.Sery

open class ComponentViewType(var baseType:Int)
{
    companion object{
        const val HEADER_TYPE = 1;
        const val CHANNEL_COURSE_VIEW_TYPE = 2;
        const val CHANNEL_SERIES_VIEW_TYPE = 3;
        const val CHANNEL_SECTION_HEADER =4
        const val CATEGORY_VIEW_TYPE = 5
        const val CATEGORY_HEADER_TYPE = 6
        const val NEW_EPISODE_ITEM_VIEW_TYPE = 7
    }

}
