package com.aliumujib.tabbarseed.utils.imageloader

import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

/**
 * Created by aliumujib on 12/05/2018.
 */

class PicassoImageLoader(private val picasso: Picasso) : ImageLoader {

    override fun loadWithAsCircle(url: String, imageView: ImageView) {
        picasso.load(url)
                .fit()
                .centerCrop()
                .transform(CropCircleTransformation())
                .into(imageView)
    }

    override fun load(url: String, imageView: ImageView, curvature: Int) {
        picasso.load(url)
                .fit()
                .centerCrop()
                .transform(RoundedCornersTransformation(curvature, curvature))
                .into(imageView)
    }

    override fun load(url: String, imageView: ImageView, fadeEffect: Boolean) {
        if (fadeEffect)
            picasso.load(url)
                    .fit()
                    .centerCrop()
                    .into(imageView)
        else
            picasso.load(url)
                    .fit()
                    .centerCrop()
                    .noFade().into(imageView)
    }


}