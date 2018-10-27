package com.aliumujib.tabbarseed.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.aliumujib.tabbarseed.R
import com.aliumujib.tabbarseed.fragments.DummyFragment
import com.aliumujib.tabbarseed.utils.FragmentHistory
import com.aliumujib.tabbarseed.utils.IMainFragmentNavigation
import com.aliumujib.tabbarseed.utils.Utils
import com.aliumujib.tabbarseed.views.FragNavController

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.weird_toolbar.*

class MainActivity : BaseActivity(), FragNavController.TransactionListener, FragNavController.RootFragmentListener, IMainFragmentNavigation {


    private val tabIconsNormal = intArrayOf(R.drawable.tab_share,
            R.drawable.tab_profile)

    private val tabIconsSelected = intArrayOf(R.drawable.tab_share,
            R.drawable.tab_profile)


    lateinit var tabs: Array<String>

    private var navController: FragNavController? = null

    private var fragmentHistory: FragmentHistory? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabs = this.resources.getStringArray(R.array.tab_name)

        initToolbar()

        initTab()

        fragmentHistory = FragmentHistory()


        navController = FragNavController.newBuilder(savedInstanceState,
                supportFragmentManager,
                R.id.content_frame)
                .transactionListener(this)
                .rootFragmentListener(this, tabs.size)
                .build()


        switchTab(0)

        bottom_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                fragmentHistory!!.push(tab.position)

                switchTab(tab.position)

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

                navController!!.clearStack()

                switchTab(tab.position)


            }
        })

    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)


    }

    private fun initTab() {
        if (bottom_tab_layout != null) {
            for (i in tabs.indices) {
                bottom_tab_layout!!.addTab(bottom_tab_layout!!.newTab())
                val tab = bottom_tab_layout!!.getTabAt(i)
                if (tab != null)
                    tab.customView = getTabView(i)
            }
        }
    }


    private fun getTabView(position: Int): View {
        val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.tab_item_bottom, null)
        val icon = view.findViewById(R.id.tab_icon) as ImageView
        val title = view.findViewById(R.id.tab_title) as TextView
        title.text = tabs[position]
        icon.setImageDrawable(Utils.setDrawableSelector(this@MainActivity, tabIconsNormal[position], tabIconsSelected[position]))
        return view
    }


    public override fun onStart() {
        super.onStart()
    }

    public override fun onStop() {

        super.onStop()
    }


    private fun switchTab(position: Int) {
        navController!!.switchTab(position)


        //        updateToolbarTitle(position);
    }


    override fun onResume() {
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {


            android.R.id.home -> {


                onBackPressed()
                return true
            }
        }


        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {

        if (!navController!!.isRootFragment) {
            navController!!.popFragment()
        } else {

            if (fragmentHistory!!.isEmpty) {
                super.onBackPressed()
            } else {


                if (fragmentHistory!!.stackSize > 1) {

                    val position = fragmentHistory!!.popPrevious()

                    switchTab(position)

                    updateTabSelection(position)

                } else {

                    switchTab(0)

                    updateTabSelection(0)

                    fragmentHistory!!.emptyStack()
                }
            }

        }
    }


    private fun updateTabSelection(currentTab: Int) {

        for (i in tabs!!.indices) {
            val selectedTab = bottom_tab_layout!!.getTabAt(i)
            selectedTab!!.customView!!.isSelected = currentTab == i
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (navController != null) {
            navController!!.onSaveInstanceState(outState)
        }
    }

    override fun pushFragment(fragment: Fragment) {
        if (navController != null) {
            navController!!.pushFragment(fragment)
        }
    }


    override fun onTabTransaction(fragment: Fragment, index: Int) {
        // If we have a backstack, show the back button
        if (supportActionBar != null && navController != null) {


            updateToolbar()

        }
    }

    private fun updateToolbar() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(!navController!!.isRootFragment)
        supportActionBar!!.setDisplayShowHomeEnabled(!navController!!.isRootFragment)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        //TransitionManager.beginDelayedTransition(title_layout, ChangeBounds())
        if (!navController!!.isRootFragment) {
            toolbar_title_view.gravity = Gravity.START
            bell_icon.visibility = View.GONE
        } else {
            toolbar_title_view.gravity = Gravity.CENTER
            bell_icon.visibility = View.VISIBLE
        }
    }


    override fun onFragmentTransaction(fragment: Fragment, transactionType: FragNavController.TransactionType) {
        //do fragmentty stuff. Maybe change title, I'm not going to tell you how to live your life
        // If we have a backstack, show the back button
        if (supportActionBar != null && navController != null) {

            updateToolbar()

        }
    }

    override fun getRootFragment(index: Int): Fragment {
        when (index) {

            FragNavController.TAB1 -> return DummyFragment()
            FragNavController.TAB2 -> return DummyFragment()
//            FragNavController.TAB5 -> return SeeAllFragment()
        }
        throw IllegalStateException("Need to send an index that we know")
    }


    //    private void updateToolbarTitle(int position){
    //
    //
    //        getSupportActionBar().setTitle(tabs[position]);
    //
    //    }


    fun updateToolbarTitle(title: String) {
        supportActionBar!!.title = title
        toolbar_title_view.text = title
    }


    companion object {
        fun start(context: Context) {
            var intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }


}
