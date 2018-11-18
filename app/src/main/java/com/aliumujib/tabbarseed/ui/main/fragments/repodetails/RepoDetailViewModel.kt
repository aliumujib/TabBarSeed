package com.aliumujib.tabbarseed.ui.main.fragments.repodetails

import com.aliumujib.tabbarseed.data.model.RepositoryEntity
import com.aliumujib.tabbarseed.ui.base.BaseFragment
import com.aliumujib.tabbarseed.ui.base.BaseViewModel
import com.aliumujib.tabbarseed.utils.IMainFragmentNavigation
import com.aliumujib.tabbarseed.utils.extensions.singleLiveDataOf

class RepoDetailViewModel(var fragmentNavigation: IMainFragmentNavigation) : BaseViewModel() {

    var repository = singleLiveDataOf<RepositoryEntity>()

    override fun setUp() {
        super.setUp()


    }

    fun setRepositoryData(data: RepositoryEntity?) {
        repository.value = data
    }

}