package com.aliumujib.tabbarseed.data.repositories

import com.aliumujib.tabbarseed.data.contracts.IGitHubListListener
import com.aliumujib.tabbarseed.data.contracts.IGithubRepository
import com.aliumujib.tabbarseed.data.model.SearchResponse
import com.aliumujib.tabbarseed.data.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepository (var apiService: ApiService,
                       var gitHubListListener: IGitHubListListener) : IGithubRepository {


    override fun fetchGithubRepositoriesMatchingFilters(hashMap: HashMap<String, Any>) {
        apiService.searchRepositories(hashMap).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                gitHubListListener.onDataFetchSucceeded(response?.body()?.items!!)
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                gitHubListListener.onDataFetchErrored(t ?: Throwable("Error fetching data"))
            }

        })
    }


}