package com.raydevelopers.newvalley.ui.view

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.raydevelopers.newvalley.models.ComponentViewType

class SpacesItemDecoration: RecyclerView.ItemDecoration {
    private val _itemOffset: Int

    constructor(itemOffset: Int) {
        _itemOffset = itemOffset
    }

    constructor(@NonNull context: Context, @DimenRes itemOffsetId: Int){
        _itemOffset = context.resources.getDimensionPixelSize(itemOffsetId)
    }

    /**
     * Applies padding to all sides of the [Rect], which is the container for the view
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,state: RecyclerView.State) {
        var position = parent.getChildAdapterPosition(view)
        val viewType = parent.adapter?.getItemViewType(position)
        if(viewType == ComponentViewType.CATEGORY_VIEW_TYPE)
        {
            if(position ==0 || position ==1)
            {
                outRect.top = 19
            }
            else{
                outRect.top = 16
            }
            if (position % 2 == 0) {
                outRect.left = _itemOffset
            }
            else{
                outRect.left = 13
                outRect.right = _itemOffset
            }
        }

    }
    }