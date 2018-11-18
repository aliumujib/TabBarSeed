package com.aliumujib.tabbarseed.ui.main.fragments.repolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliumujib.tabbarseed.data.contracts.IGithubRepository
import com.aliumujib.tabbarseed.ui.base.BaseFragment
import com.aliumujib.tabbarseed.utils.IMainFragmentNavigation

class RepoListViewModelModelFactory(private var fragmentNavigation: IMainFragmentNavigation,
                                    private val repository: IGithubRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepositoryViewModel(repository, fragmentNavigation) as T
    }
}