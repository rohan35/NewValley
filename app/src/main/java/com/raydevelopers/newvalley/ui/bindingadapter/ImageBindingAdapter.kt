package com.raydevelopers.newvalley.ui.bindingadapter

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.raydevelopers.newvalley.R

@BindingAdapter("imageUrl")
fun loadImageURL(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView.context)
            .load(imageUrl)
            .placeholder(getCircularProgressDrawable(imageView.context))
            .dontAnimate()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .into(imageView)
    }
}
    /**
     *
     * @param context
     * @return
     */
    fun getCircularProgressDrawable(context: Context): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = context.resources.getDimension(R.dimen.dp_1)
        circularProgressDrawable.centerRadius = context.resources.getDimension(R.dimen.dp_10)
        circularProgressDrawable.start()
        return circularProgressDrawable
    }