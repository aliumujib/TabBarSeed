package com.aliumujib.tabbarseed.utils.extensions

import retrofit2.Retrofit

internal inline fun <reified T> Retrofit.create(): T = create(T::class.java)
