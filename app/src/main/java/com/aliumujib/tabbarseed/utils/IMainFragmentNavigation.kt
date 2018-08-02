package com.aliumujib.tabbarseed.utils

import android.support.v4.app.Fragment


interface IMainFragmentNavigation {

    //fun setUp()

    fun pushFragment(fragment: Fragment)


}

//class MainFragmentNavigation(private var navController: FragNavController,
//                             private var fragmentHistory: FragmentHistory) : IMainFragmentNavigation {
//
//    override fun pushFragment(fragment: Fragment) {
//
//    }
//
//}