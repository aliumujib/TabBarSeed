package com.aliumujib.tabbarseed.di.scopes

import javax.inject.Scope

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@Scope
annotation class TabBarSeedApplicationScope