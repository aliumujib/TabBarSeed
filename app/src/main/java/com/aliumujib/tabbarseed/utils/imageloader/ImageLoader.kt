package com.aliumujib.tabbarseed.utils.imageloader

import android.widget.ImageView

/**
 * Created by aliumujib on 12/05/2018.
 */

interface ImageLoader {
    //fun load(url: String, imageView: ImageView, callback: (Boolean) -> Unit)
    fun load(url: String, imageView: ImageView, fadeEffect: Boolean = true)

    fun loadWithAsCircle(url: String, imageView: ImageView)

    fun load(url: String, imageView: ImageView, curvature: Int)
}

