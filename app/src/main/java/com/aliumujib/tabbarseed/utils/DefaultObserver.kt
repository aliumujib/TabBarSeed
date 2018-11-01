package com.aliumujib.tabbarseed.utils

import android.util.Log
import io.reactivex.observers.DisposableObserver

/**
 * Created by aliumujib on 12/05/2018.
 */

open class DefaultObserver<T> : DisposableObserver<T>() {
    val TAG = javaClass.canonicalName

    override fun onNext(t: T) {
        // Intentionally empty.
    }

    override fun onComplete() {
        // Intentionally empty.
    }

    override fun onError(exception: Throwable) {
        // Intentionally empty. Haha no its not ;)
        Log.d(TAG, exception.localizedMessage, exception)
    }
}