package com.aliumujib.tabbarseed.data.contracts

import com.aliumujib.tabbarseed.data.model.Repository
import io.reactivex.Observable
import io.reactivex.Single


interface IGithubRepository {
    fun fetchGithubRepositoriesMatchingFilters(hashMap: HashMap<String, Any>): Observable<List<Repository>>
}

interface IGitHubListListener {

    fun onDataFetchSucceeded(data: List<Repository>)

    fun onDataFetchErrored(error: Throwable)

}