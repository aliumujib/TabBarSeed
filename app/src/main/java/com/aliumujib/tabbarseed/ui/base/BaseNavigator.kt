package com.aliumujib.tabbarseed.ui.base

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.aliumujib.tabbarseed.utils.extensions.TAG


/**
 * Created by aliumujib on 08/06/2018.
 */

abstract class BaseNavigator {

    abstract fun getFragmentManager(): FragmentManager?

    @IdRes
    abstract fun getLayoutID(): Int

    protected fun findFragmentInstance(tag: String): Fragment? {
        return getFragmentManager()?.findFragmentByTag(tag)
    }

    protected fun addFragmentWithBackStack(fragment: Fragment, tag: String = fragment.TAG()) {
        val transaction = getFragmentManager()?.beginTransaction()
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction?.add(getLayoutID(), fragment)?.addToBackStack(tag)?.commit()
    }

    protected fun replaceFragment(fragment: Fragment) {
        val transaction = getFragmentManager()?.beginTransaction()
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction?.replace(getLayoutID(), fragment)?.commit()
    }
}