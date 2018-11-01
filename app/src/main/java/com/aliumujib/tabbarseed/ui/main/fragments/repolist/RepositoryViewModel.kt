package com.aliumujib.tabbarseed.ui.main.fragments.repolist

import androidx.lifecycle.ViewModel
import com.aliumujib.tabbarseed.data.contracts.IGithubRepository
import com.aliumujib.tabbarseed.data.model.Repository
import com.aliumujib.tabbarseed.utils.DefaultObserver
import com.aliumujib.tabbarseed.utils.extensions.mutableLiveDataOf
import com.aliumujib.tabbarseed.utils.extensions.singleLiveDataOf
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepositoryViewModel(var githubRepository: IGithubRepository) : ViewModel() {

    var hideLoading = singleLiveDataOf<Void>()
    var showLoading = singleLiveDataOf<Void>()
    var compositeDisposable = CompositeDisposable()

    var data = mutableLiveDataOf<List<Repository>>()
    var error = mutableLiveDataOf<Throwable>()


    private fun hideViewLoading() {
        hideLoading.invoke()
    }

    private fun showViewLoading() {
        showLoading.invoke()
    }


    fun getReposData() {
        var hashMap = HashMap<String, Any>()
        hashMap["q"] = "android+language:java+language:kotlin"
        hashMap["sort"] = "stars"
        hashMap["order"] = "desc"

        compositeDisposable
                .addAll(githubRepository.fetchGithubRepositoriesMatchingFilters(hashMap)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            showViewLoading()
                        }
                        .subscribeWith(RepoObserver()))

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private fun onRepoListFetchSucceeded(t: List<Repository>) {
        hideViewLoading()
        data.value = t
    }

    private fun onRepoListFetchFailed(exception: Throwable) {
        hideViewLoading()
        error.value = exception
    }


    inner class RepoObserver : DefaultObserver<List<Repository>>() {
        override fun onNext(t: List<Repository>) {
            super.onNext(t)
            onRepoListFetchSucceeded(t)
        }


        override fun onError(exception: Throwable) {
            super.onError(exception)
            onRepoListFetchFailed(exception)
        }
    }


}