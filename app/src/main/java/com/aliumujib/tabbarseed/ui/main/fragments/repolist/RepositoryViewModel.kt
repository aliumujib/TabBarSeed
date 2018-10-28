package com.aliumujib.tabbarseed.ui.main.fragments.repolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliumujib.tabbarseed.data.contracts.IGitHubListListener
import com.aliumujib.tabbarseed.data.contracts.IGithubRepository
import com.aliumujib.tabbarseed.data.model.Repository
import com.aliumujib.tabbarseed.data.repositories.GithubRepository
import com.aliumujib.tabbarseed.utils.call

class RepositoryViewModel(var githubRepository: IGithubRepository) : ViewModel() {

    var hideLoading: MutableLiveData<Void> = MutableLiveData()
    var showLoading: MutableLiveData<Void> = MutableLiveData()


    var data: MutableLiveData<List<Repository>> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()


    private var gitHubListListener = object : IGitHubListListener {
        override fun onDataFetchSucceeded(data: List<Repository>) {
            hideViewLoading()
            this@RepositoryViewModel.data.value = data
        }

        override fun onDataFetchErrored(error: Throwable) {
            hideViewLoading()
            this@RepositoryViewModel.error.value = error
        }
    }

    init {
        (githubRepository as GithubRepository).gitHubListListener = gitHubListListener
    }

    fun hideViewLoading() {
        hideLoading.call()
    }

    fun showViewLoading() {
        showLoading.call()
    }



    fun getReposData() {
        var hashMap = HashMap<String, Any>()
        hashMap["q"] = "android+language:java+language:kotlin"
        hashMap["sort"] = "stars"
        hashMap["order"] = "desc"

        githubRepository.fetchGithubRepositoriesMatchingFilters(hashMap)
        showViewLoading()
    }

}