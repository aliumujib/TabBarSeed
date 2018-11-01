package com.aliumujib.tabbarseed.data.retrofit

import com.aliumujib.tabbarseed.data.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface ApiService {
//https://api.github.com/search/repositories?q=android+language:java+language:kotlin&sort=stars&order=desc

    @GET("search/repositories")
    fun searchRepositories(@QueryMap headers: Map<String, @JvmSuppressWildcards Any>): Single<SearchResponse>

}