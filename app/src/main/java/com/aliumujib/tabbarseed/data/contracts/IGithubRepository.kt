package com.aliumujib.tabbarseed.data.contracts

import com.aliumujib.tabbarseed.data.model.Repository


interface IGithubRepository {
    fun fetchGithubRepositoriesMatchingFilters(hashMap: HashMap<String, Any>)
}

interface IGitHubListListener {

    fun onDataFetchSucceeded(data: List<Repository>)

    fun onDataFetchErrored(error: Throwable)

}