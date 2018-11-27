package com.aliumujib.tabbarseed.di.components

import com.aliumujib.tabbarseed.ApplicationClass
import com.aliumujib.tabbarseed.di.modules.global.TabBarSeedActivityBuilder
import com.aliumujib.tabbarseed.di.modules.global.TabBarSeedApplicationModule
import com.aliumujib.tabbarseed.di.scopes.TabBarSeedApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

/**
 * Created by ayokunlepaul on 27/11/2018.
 */
@Component(modules = [AndroidSupportInjectionModule::class, AndroidInjectionModule::class, TabBarSeedActivityBuilder::class, TabBarSeedApplicationModule::class])
@TabBarSeedApplicationScope
interface TabBarSeedApplicationComponent: AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance fun bindApplicationInstance(application: ApplicationClass): Builder
        fun buildTabBarApplicationComponent(): TabBarSeedApplicationComponent
    }
}