package com.aliumujib.tabbarseed.utils.binding

import androidx.databinding.DataBindingComponent
import com.aliumujib.tabbarseed.utils.imageloader.ImageLoader


class BindingComponentImpl(var imageLoader: ImageLoader) : DataBindingComponent {

    override fun getImageBindingAdapter(): ImageBindingAdapter {
        return ImageBindingAdapter(imageLoader)
    }

}