package com.raydevelopers.newvalley.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raydevelopers.newvalley.BR
import com.raydevelopers.newvalley.R
import com.raydevelopers.newvalley.databinding.CategoryItemLayoutBinding
import com.raydevelopers.newvalley.databinding.ChannelSectionHeaderLayoutBinding
import com.raydevelopers.newvalley.databinding.ListItemSeperatorBinding
import com.raydevelopers.newvalley.databinding.SingleTextHeaderLayoutBinding
import com.raydevelopers.newvalley.models.ComponentViewType
import com.raydevelopers.newvalley.models.HeaderAdapterInfo
import com.raydevelopers.newvalley.models.SingleHeaderItem
import com.raydevelopers.newvalley.models.category.CategoryX
import com.raydevelopers.newvalley.models.channel.ChannelX
import com.raydevelopers.newvalley.ui.view.SpacesItemDecoration
import com.raydevelopers.newvalley.utility.CATEGORIES_TITLE

class AllChannelsRecyclerAdapter(var headerAdapterInfo: HeaderAdapterInfo) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mHeaderList: ArrayList<ComponentViewType> = headerAdapterInfo.HeaderList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ComponentViewType.HEADER_TYPE -> {
                SingleTextHeaderViewHolder(
                    SingleTextHeaderLayoutBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    ),parent.context
                )
            }
            ComponentViewType.SEPARATOR_VIEW_TYPE ->
            {
                LineSeparatorViewHolder(
                    ListItemSeperatorBinding.inflate(
                        LayoutInflater.from(
                        parent.context
                    ),parent,false
                ))
            }
            else -> {
                ChannelSectionHeaderViewHolder(
                    ChannelSectionHeaderLayoutBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                ,parent.context
                )
            }

        }
    }

    override fun getItemCount(): Int {
        return mHeaderList.size
    }

    override fun getItemViewType(position: Int): Int {
        return mHeaderList[position].baseType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ComponentViewType.HEADER_TYPE -> {
                (holder as SingleTextHeaderViewHolder).bind(mHeaderList[position] as SingleHeaderItem)
            }
            ComponentViewType.CHANNEL_SECTION_HEADER -> {
                (holder as ChannelSectionHeaderViewHolder).bind(mHeaderList[position] as ChannelX)
            }
            ComponentViewType.SEPARATOR_VIEW_TYPE -> {
                (holder as LineSeparatorViewHolder).bind()
            }
        }
    }

    inner class ChannelSectionHeaderViewHolder(private var mBinding: ChannelSectionHeaderLayoutBinding
    ,private var context: Context) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(data: ChannelX) {
            mBinding.setVariable(BR.channelItem, data)
            var componentList:MutableList<ComponentViewType>? = if((data.endIndex - data.startIndex) > 6) {
                headerAdapterInfo.sectionList?.subList(data.startIndex, data.startIndex.plus(6))
            } else {
                headerAdapterInfo.sectionList?.subList(data.startIndex, data.endIndex)
            }

            if (!componentList.isNullOrEmpty())
            {
                mBinding.sectionRv.sectionItemsRv.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                mBinding.sectionRv.sectionItemsRv.setHasFixedSize(true)
                mBinding.sectionRv.sectionItemsRv.adapter =
                    SectionListRecyclerAdapter(componentList)
            }

        }
    }

    inner class SingleTextHeaderViewHolder(private var mBinding: SingleTextHeaderLayoutBinding,
                                           private var context: Context) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(data: SingleHeaderItem) {
            mBinding.setVariable(BR.item, data)
            var componentList =
                headerAdapterInfo.sectionList?.subList(data.startIndex, data.endIndex)

                if(data.title == CATEGORIES_TITLE)
                {
                    mBinding.sectionRv.sectionItemsRv.layoutManager =
                        GridLayoutManager(context, 2)
                    if(mBinding.sectionRv.sectionItemsRv.itemDecorationCount<1)
                    mBinding.sectionRv.sectionItemsRv.addItemDecoration(SpacesItemDecoration(context,
                        R.dimen.dp_20))
                    mBinding.sectionRv.sectionItemsRv.setHasFixedSize(true)
                }
                else{
                    mBinding.sectionRv.sectionItemsRv.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    mBinding.sectionRv.sectionItemsRv.setHasFixedSize(true)
                    componentList = if((data.endIndex - data.startIndex) > 6) {
                        headerAdapterInfo.sectionList?.subList(data.startIndex, data.startIndex.plus(6))
                    } else {
                        headerAdapterInfo.sectionList?.subList(data.startIndex, data.endIndex)
                    }
                }
            if(!componentList.isNullOrEmpty())
                mBinding.sectionRv.sectionItemsRv.adapter =
                    SectionListRecyclerAdapter(componentList)

        }
    }
    inner class LineSeparatorViewHolder(private val mBinding:ListItemSeperatorBinding)
        :RecyclerView.ViewHolder(mBinding.root)
    {
        fun bind()
        {
            mBinding.executePendingBindings()
        }
    }
}
