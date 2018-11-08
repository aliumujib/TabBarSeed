package com.aliumujib.tabbarseed

import androidx.databinding.DataBindingUtil
import androidx.multidex.MultiDexApplication
import com.aliumujib.tabbarseed.utils.imageloader.ImageLoader
import com.aliumujib.tabbarseed.utils.binding.BindingComponentImpl
import com.aliumujib.tabbarseed.utils.imageloader.PicassoImageLoader
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import com.squareup.picasso.Picasso


/**
 * Created by aliumujib on 14/05/2018.
 */

class ApplicationClass : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        applicationClass = this


        Stetho.initializeWithDefaults(this)

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }

        initDependencies()
    }


    private fun initDependencies() {

        DataBindingUtil.setDefaultComponent(BindingComponentImpl(PicassoImageLoader(Picasso.get())))

    }

    override fun onTerminate() {
        super.onTerminate()
    }

    companion object {
        lateinit var applicationClass: ApplicationClass
        fun getInstance(): ApplicationClass = applicationClass
    }

}