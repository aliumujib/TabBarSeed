package com.aliumujib.tabbarseed.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.aliumujib.tabbarseed.R


class DummyFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dummy, container, false)
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                DummyFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}