package com.aliumujib.tabbarseed.ui.main.fragments.repodetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliumujib.tabbarseed.ui.base.BaseFragment
import com.aliumujib.tabbarseed.utils.IMainFragmentNavigation

class RepoDetailsViewModelModelFactory(private var fragmentNavigation: IMainFragmentNavigation) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepoDetailViewModel(fragmentNavigation) as T
    }
}