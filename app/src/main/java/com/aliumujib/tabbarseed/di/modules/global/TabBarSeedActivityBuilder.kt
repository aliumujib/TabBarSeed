package com.aliumujib.tabbarseed.di.modules.global

import com.aliumujib.tabbarseed.di.scopes.PerActivity
import com.aliumujib.tabbarseed.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by ayokunlepaul on 27/11/2018.
 */
@Module
abstract class TabBarSeedActivityBuilder {

    @ContributesAndroidInjector @PerActivity
    abstract fun bindToMainActivity(): MainActivity

    /**
     * Other activities and their sub-components should be added here
     */
}