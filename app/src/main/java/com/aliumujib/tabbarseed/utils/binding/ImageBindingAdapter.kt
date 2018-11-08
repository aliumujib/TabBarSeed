package com.aliumujib.tabbarseed.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.aliumujib.tabbarseed.utils.imageloader.ImageLoader


class ImageBindingAdapter(private val imageLoader: ImageLoader) {

    @BindingAdapter("bind:imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        imageLoader.load(url = imageUrl, imageView = view, fadeEffect = true)
    }


}