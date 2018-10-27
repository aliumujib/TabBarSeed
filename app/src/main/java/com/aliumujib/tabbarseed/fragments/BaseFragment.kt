package com.aliumujib.tabbarseed.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Created by f22labs on 07/03/17.
 */

open class BaseFragment : Fragment() {


    lateinit var mFragmentNavigation: FragmentNavigation

    override open fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigation) {
            mFragmentNavigation = context
        }
    }

    interface FragmentNavigation {
        fun pushFragment(fragment: Fragment)
    }

    companion object {

        val ARGS_INSTANCE = "com.f22labs.instalikefragmenttransaction"
    }


}
