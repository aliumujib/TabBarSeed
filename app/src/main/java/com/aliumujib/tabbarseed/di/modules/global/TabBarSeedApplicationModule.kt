package com.aliumujib.tabbarseed.di.modules.global

import android.content.Context
import com.aliumujib.tabbarseed.ApplicationClass
import com.aliumujib.tabbarseed.di.modules.global.network.RetrofitModule
import com.aliumujib.tabbarseed.di.scopes.TabBarSeedApplicationScope
import dagger.Binds
import dagger.Module

/**
 * Created by ayokunlepaul on 27/11/2018.
 */
@Module(includes = [RetrofitModule::class])
abstract class TabBarSeedApplicationModule {

    @TabBarSeedApplicationScope @Binds
    internal abstract fun provideApplicationContext(application: ApplicationClass): Context

    /**
     * Other external instances should be provided here.
     */
}