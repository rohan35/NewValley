package com.raydevelopers.newvalley.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raydevelopers.newvalley.BR
import com.raydevelopers.newvalley.databinding.*
import com.raydevelopers.newvalley.models.ComponentViewType
import com.raydevelopers.newvalley.models.category.CategoryX
import com.raydevelopers.newvalley.models.channel.ChannelMediaListItem
import com.raydevelopers.newvalley.models.newepisode.Media

class SectionListRecyclerAdapter(private var mComponentArrayList: List<ComponentViewType>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ComponentViewType.CHANNEL_COURSE_VIEW_TYPE -> {
                ChannelCourseViewHolder(
                    ChannelCourseItemLayoutBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }
            ComponentViewType.CHANNEL_SERIES_VIEW_TYPE -> {
                ChannelSeriesViewHolder(
                    ChannelSeriesItemLayoutBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }

            ComponentViewType.CATEGORY_VIEW_TYPE -> {
                CategoryViewHolder(
                    CategoryItemLayoutBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }
            else -> {
                NewEpisodeSectionViewHolder(
                    NewEpisodeItemLayoutBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }

        }
    }

    override fun getItemCount(): Int {
        return mComponentArrayList.size;
    }

    override fun getItemViewType(position: Int): Int {
        return mComponentArrayList[position].baseType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ComponentViewType.NEW_EPISODE_ITEM_VIEW_TYPE -> {
                (holder as NewEpisodeSectionViewHolder).bind(mComponentArrayList[position] as Media)
            }
            ComponentViewType.CHANNEL_COURSE_VIEW_TYPE -> {
                (holder as ChannelCourseViewHolder).bind(mComponentArrayList[position] as ChannelMediaListItem)
            }
            ComponentViewType.CHANNEL_SERIES_VIEW_TYPE -> {
                (holder as ChannelSeriesViewHolder).bind(mComponentArrayList[position] as ChannelMediaListItem)
            }
            ComponentViewType.CATEGORY_VIEW_TYPE -> {
                (holder as CategoryViewHolder).bind(mComponentArrayList[position] as CategoryX)
            }
        }
    }

    inner class NewEpisodeSectionViewHolder(private var mBinding: NewEpisodeItemLayoutBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(data: Media) {
            mBinding.setVariable(BR.newEpisodeItem, data)
        }

    }

    inner class ChannelCourseViewHolder(private var mBinding: ChannelCourseItemLayoutBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(data: ChannelMediaListItem) {
            mBinding.setVariable(BR.courseItem, data)
        }
    }

    inner class ChannelSeriesViewHolder(private var mBinding: ChannelSeriesItemLayoutBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(data: ChannelMediaListItem) {
            mBinding.setVariable(BR.seriesItem, data)
        }
    }

    inner class CategoryViewHolder(private var mBinding: CategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(data: CategoryX) {
            mBinding.setVariable(BR.categoryName, data)
        }
    }

}
