package com.aliumujib.tabbarseed.data.contracts

import com.aliumujib.tabbarseed.data.model.RepositoryEntity
import io.reactivex.Observable


interface IGithubRepository {
    fun fetchGithubRepositoriesMatchingFilters(hashMap: HashMap<String, Any>): Observable<List<RepositoryEntity>>
}

interface IGitHubListListener {

    fun onDataFetchSucceeded(data: List<RepositoryEntity>)

    fun onDataFetchErrored(error: Throwable)

}