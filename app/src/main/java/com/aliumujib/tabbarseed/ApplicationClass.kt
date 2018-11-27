package com.aliumujib.tabbarseed

import androidx.databinding.DataBindingUtil
import com.aliumujib.tabbarseed.di.components.DaggerTabBarSeedApplicationComponent
import com.aliumujib.tabbarseed.utils.TabBarSeedLog
import com.aliumujib.tabbarseed.utils.binding.BindingComponentImpl
import com.aliumujib.tabbarseed.utils.imageloader.PicassoImageLoader
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


/**
 * Created by aliumujib on 14/05/2018.
 */

class ApplicationClass : DaggerApplication(){

    override fun onCreate() {
        super.onCreate()
        applicationClass = this


        Stetho.initializeWithDefaults(this)

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        TabBarSeedLog.init()
        initDependencies()
    }


    private fun initDependencies() {

        DataBindingUtil.setDefaultComponent(BindingComponentImpl(PicassoImageLoader(Picasso.get())))

    }

    override fun onTerminate() {
        super.onTerminate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerTabBarSeedApplicationComponent.builder().bindApplicationInstance(this).buildTabBarApplicationComponent()

    companion object {
        lateinit var applicationClass: ApplicationClass
        fun getInstance(): ApplicationClass = applicationClass
    }

}