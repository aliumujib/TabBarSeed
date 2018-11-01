package com.aliumujib.tabbarseed.utils.extensions

import androidx.lifecycle.MutableLiveData
import com.aliumujib.tabbarseed.utils.SingleLiveData

internal fun <T> mutableLiveDataOf(): MutableLiveData<T> = MutableLiveData()
internal fun <T> singleLiveDataOf(): SingleLiveData<T> = SingleLiveData()
