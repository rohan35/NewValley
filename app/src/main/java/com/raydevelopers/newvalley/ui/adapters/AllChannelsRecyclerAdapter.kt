package com.raydevelopers.newvalley.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raydevelopers.newvalley.BR
import com.raydevelopers.newvalley.databinding.ChannelSectionHeaderLayoutBinding
import com.raydevelopers.newvalley.databinding.SingleTextHeaderLayoutBinding
import com.raydevelopers.newvalley.models.ComponentViewType
import com.raydevelopers.newvalley.models.HeaderAdapterInfo
import com.raydevelopers.newvalley.models.SingleHeaderItem
import com.raydevelopers.newvalley.models.channel.ChannelX
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
        }
    }

    inner class ChannelSectionHeaderViewHolder(private var mBinding: ChannelSectionHeaderLayoutBinding
    ,private var context: Context) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(data: ChannelX) {
            mBinding.setVariable(BR.channelItem, data)
            val componentList =
                headerAdapterInfo.sectionList?.subList(data.startIndex, data.endIndex)
            if (!componentList.isNullOrEmpty())
            {
                mBinding.sectionRv.sectionItemsRv.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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
            val componentList =
                headerAdapterInfo.sectionList?.subList(data.startIndex, data.endIndex)
            if (!componentList.isNullOrEmpty())
            {
                if(data.title == CATEGORIES_TITLE)
                {
                    mBinding.sectionRv.sectionItemsRv.layoutManager =
                        GridLayoutManager(context, 2)
                    mBinding.sectionRv.sectionItemsRv.setHasFixedSize(true)
                }
                else{
                    mBinding.sectionRv.sectionItemsRv.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                }
                mBinding.sectionRv.sectionItemsRv.adapter =
                    SectionListRecyclerAdapter(componentList)
            }
        }
    }
}
