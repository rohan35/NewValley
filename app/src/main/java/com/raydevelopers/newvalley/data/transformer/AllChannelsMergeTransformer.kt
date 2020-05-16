package com.raydevelopers.newvalley.data.transformer

import androidx.lifecycle.MutableLiveData
import com.raydevelopers.newvalley.data.usecase.AllChannelMergeUseCase
import com.raydevelopers.newvalley.models.ComponentViewType
import com.raydevelopers.newvalley.models.HeaderAdapterInfo
import com.raydevelopers.newvalley.models.SingleHeaderItem
import com.raydevelopers.newvalley.models.category.Category
import com.raydevelopers.newvalley.models.category.CategoryX
import com.raydevelopers.newvalley.models.channel.Channel
import com.raydevelopers.newvalley.models.channel.ChannelMediaListItem
import com.raydevelopers.newvalley.models.channel.ChannelX
import com.raydevelopers.newvalley.models.newepisode.Media
import com.raydevelopers.newvalley.models.newepisode.NewEpisode
import com.raydevelopers.newvalley.utility.CATEGORIES_TITLE

class AllChannelsMergeTransformer : AllChannelMergeUseCase {
    override suspend fun mergeCategories(
        mergedResponseLiveData: MutableLiveData<HeaderAdapterInfo>,
        newEpisode:NewEpisode,
        channel: Channel,
        category: Category
    ) {
        val headerList = ArrayList<ComponentViewType>()
        val sectionList = ArrayList<ComponentViewType>()
        var currentItemIndex =0;
        // add new episodes
        var newEpisodeHeaderItem = SingleHeaderItem("New Episode")
        headerList.add(newEpisodeHeaderItem)
        newEpisodeHeaderItem.startIndex = 0;
        newEpisode?.data?.media?.forEach { mediaItem ->
            mediaItem?.let {
                sectionList.add(Media(mediaItem.channel, mediaItem.coverAsset, mediaItem.title))
                currentItemIndex += 1;
            }
        }
        newEpisodeHeaderItem.endIndex = currentItemIndex

        channel?.data?.channels?.forEach { channelXList ->
            val id = channelXList?.id
            var headerItem = ChannelX(
                iconAsset = channelXList?.iconAsset,
                id = channelXList?.id,
                mediaCount = channelXList?.mediaCount,
                title = channelXList?.title
            )
            headerItem.startIndex = currentItemIndex
            headerList.add(headerItem)
            if (channelXList?.series.isNullOrEmpty()) {
                channelXList?.latestMedia?.forEach { latestMedia ->
                    val channelMediaListItem = ChannelMediaListItem()
                    channelMediaListItem.coverAsset = latestMedia?.coverAsset?.url
                    channelMediaListItem.title = latestMedia?.title
                    channelMediaListItem.type = latestMedia?.type
                    sectionList.add(channelMediaListItem)
                    currentItemIndex += 1;
                }
            } else {
                channelXList?.series?.forEach { series ->
                    val channelMediaListItem = ChannelMediaListItem()
                    channelMediaListItem.baseType = ComponentViewType.CHANNEL_SERIES_VIEW_TYPE
                    channelMediaListItem.coverAsset = series?.coverAsset?.url
                    channelMediaListItem.title = series?.title
                    channelMediaListItem.id = series?.id
                    sectionList.add(channelMediaListItem)
                    currentItemIndex += 1;
                }
            }
            headerItem.endIndex = currentItemIndex
        }
        // add categories
        var categoryHeaderItem = SingleHeaderItem(CATEGORIES_TITLE)
        categoryHeaderItem.startIndex =currentItemIndex
        headerList.add(categoryHeaderItem)

        category?.data?.categories?.forEach {categoryItem->
            categoryItem?.let {
                sectionList.add(CategoryX(it.name))
                currentItemIndex += 1
            }
        }
        categoryHeaderItem.endIndex = currentItemIndex
        mergedResponseLiveData.postValue(HeaderAdapterInfo(headerList,sectionList))
    }
}