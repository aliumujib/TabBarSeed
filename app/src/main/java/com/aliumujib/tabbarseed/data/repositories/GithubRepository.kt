package com.aliumujib.tabbarseed.data.repositories

import com.aliumujib.tabbarseed.data.contracts.IGithubRepository
import com.aliumujib.tabbarseed.data.model.RepositoryEntity
import com.aliumujib.tabbarseed.data.retrofit.ApiService
import com.aliumujib.tabbarseed.ui.base.BaseFragment
import io.reactivex.Observable

class GithubRepository(var apiService: ApiService) : IGithubRepository {

    override fun fetchGithubRepositoriesMatchingFilters(hashMap: HashMap<String, Any>): Observable<List<RepositoryEntity>> {
        return apiService.searchRepositories(hashMap).map {
            it.items ?: mutableListOf()
        }.toObservable()
    }




}