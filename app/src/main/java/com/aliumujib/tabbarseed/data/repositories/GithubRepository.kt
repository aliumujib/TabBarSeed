package com.aliumujib.tabbarseed.data.repositories

import com.aliumujib.tabbarseed.data.contracts.IGithubRepository
import com.aliumujib.tabbarseed.data.model.Repository
import com.aliumujib.tabbarseed.data.retrofit.ApiService
import io.reactivex.Observable
import io.reactivex.Single

class GithubRepository (var apiService: ApiService) : IGithubRepository {

    override fun fetchGithubRepositoriesMatchingFilters(hashMap: HashMap<String, Any>): Observable<List<Repository>> {
        return apiService.searchRepositories(hashMap).map {
            it.items ?: mutableListOf()
        }.toObservable()
    }


}