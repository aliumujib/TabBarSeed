package com.aliumujib.tabbarseed.utils

import androidx.lifecycle.MutableLiveData


fun<T> MutableLiveData<T>.call() {
    value = null
}