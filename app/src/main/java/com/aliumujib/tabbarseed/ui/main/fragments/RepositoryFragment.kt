package com.aliumujib.tabbarseed.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliumujib.retrofit_sample.model.Repository
import com.aliumujib.tabbarseed.R
import com.aliumujib.tabbarseed.data.contracts.IGitHubListListener
import com.aliumujib.tabbarseed.data.contracts.IGithubRepository
import com.aliumujib.tabbarseed.data.repositories.GithubRepository
import com.aliumujib.tabbarseed.data.retrofit.ApiClient
import com.aliumujib.tabbarseed.ui.adapter.MyRepositoryRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_repository_list.*


class RepositoryFragment : Fragment() {

    lateinit var recyclerViewAdapter: MyRepositoryRecyclerViewAdapter
    val apiClient = ApiClient()
    lateinit var githubRepository: IGithubRepository

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


    private var gitHubListListener = object : IGitHubListListener {
        override fun onDataFetchSucceeded(data: List<Repository>) {
            hideLoading()
            recyclerViewAdapter.addItems(data)
        }

        override fun onDataFetchErrored(error: Throwable) {
            hideLoading()
            Toast.makeText(context!!, error.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        githubRepository = GithubRepository(apiClient.service, gitHubListListener)
        recyclerViewAdapter = MyRepositoryRecyclerViewAdapter(mutableListOf())
        // Set the adapter
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = recyclerViewAdapter

        var hashMap = HashMap<String, Any>()
        hashMap["q"] = "android+language:java+language:kotlin"
        hashMap["sort"] = "stars"
        hashMap["order"] = "desc"

        githubRepository.fetchGithubRepositoriesMatchingFilters(hashMap)
        showLoading()

    }

    private fun showLoading(){
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideLoading(){
        progress_bar.visibility = View.GONE
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
