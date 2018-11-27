package com.aliumujib.tabbarseed.data.retrofit

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by ayokunlepaul on 27/11/2018.
 */
class RedirectInterceptor: Interceptor {

    companion object {
        private const val MAX_REPEATS = 5
    }

    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain!!.request()
        var response = chain.proceed(request)
        if (response.code() == 302) {
            var repeats = MAX_REPEATS
            while (repeats-- > 0 && response.code() == 302) {
                response = chain.proceed(request.newBuilder().build())
            }
        }
        return response
    }
}