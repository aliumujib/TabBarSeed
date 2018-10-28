package com.aliumujib.tabbarseed.ui.main.fragments.repolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliumujib.tabbarseed.data.contracts.IGithubRepository

class RepoListViewModelModelFactory(
    private val repository: IGithubRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepositoryViewModel(repository) as T
    }
}