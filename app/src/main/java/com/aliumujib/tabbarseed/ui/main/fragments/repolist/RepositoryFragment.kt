package com.aliumujib.tabbarseed.ui.main.fragments.repolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliumujib.tabbarseed.R
import com.aliumujib.tabbarseed.data.contracts.IGithubRepository
import com.aliumujib.tabbarseed.data.model.RepositoryEntity
import com.aliumujib.tabbarseed.data.repositories.GithubRepository
import com.aliumujib.tabbarseed.data.retrofit.ApiClient
import com.aliumujib.tabbarseed.ui.adapter.base.SingleLayoutAdapter
import com.aliumujib.tabbarseed.utils.common.NotNullObserver
import kotlinx.android.synthetic.main.fragment_repository_list.*


class RepositoryFragment : Fragment() {

    lateinit var recyclerViewAdapter: SingleLayoutAdapter<RepositoryEntity>
    val apiClient = ApiClient()
    lateinit var githubRepository: IGithubRepository
    lateinit var viewModel: RepositoryViewModel
    lateinit var viewModelFactory: RepoListViewModelModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_repository_list, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        githubRepository = GithubRepository(apiClient.service)
        recyclerViewAdapter = SingleLayoutAdapter(R.layout.repo_list_item)
        // Set the adapter
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = recyclerViewAdapter

        viewModelFactory = RepoListViewModelModelFactory(githubRepository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepositoryViewModel::class.java)
        viewModel.getReposData()

        observeLoading()

        viewModel.data.observe(this.viewLifecycleOwner, NotNullObserver{ repos ->
            recyclerViewAdapter.setData(repos)
        })

    }

    private fun observeLoading() {
        viewModel.hideLoading.observe(viewLifecycleOwner , Observer{  meh ->
            progress_bar.visibility = View.GONE
        })
        viewModel.showLoading.observe(viewLifecycleOwner, Observer { meh ->
            progress_bar.visibility = View.VISIBLE
        })

    }


    companion object {

        @JvmStatic
        fun newInstance() =
                RepositoryFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
