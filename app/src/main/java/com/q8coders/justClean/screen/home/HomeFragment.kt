package com.q8coders.justClean.screen.home

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import com.q8coders.justClean.R
import com.q8coders.justClean.application.MyApplication
import com.q8coders.justClean.base.BaseFragment
import com.q8coders.justClean.di.components.DaggerHomeComponent
import com.q8coders.justClean.di.modules.HomeModule
import com.q8coders.justClean.utility.MyUtility
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

/**
 * @Created by shahid on 8/26/2018.
 */
class HomeFragment : BaseFragment(), HomeView {

    @Inject
    lateinit var homePresenterImp: HomePresenterImp


    override fun setLayoutResource(): Int = R.layout.home_fragment


    override fun viewIsReady() {
        addTabs()

    }

    override fun setFragmentTitle(actionBarTitle: TextView?, text: String?) {

    }

    override fun resolveDependency() {
        DaggerHomeComponent.builder()
                .applicationComponent(MyApplication.applicationComponent)
                .homeModule(HomeModule(this))
                .build().injectView(this)
    }

    override fun retry() {

    }

    private fun addTabs() {
        tabLayout?.addTab(tabLayout.newTab().setText(getString(R.string.popular_movies)))
        tabLayout?.addTab(tabLayout.newTab().setText(getString(R.string.top_rated)))
        tabLayout?.addTab(tabLayout.newTab().setText(getString(R.string.upcoming)))
        tabLayout?.tabGravity = TabLayout.GRAVITY_FILL

        val tabsTitle = arrayOf(getString(R.string.popular_movies), getString(R.string.top_rated), getString(R.string.upcoming))

        val adapter = TabPagerAdapter(childFragmentManager, tabLayout.tabCount, tabsTitle)
        viewPager?.adapter = adapter
        viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        viewPager?.offscreenPageLimit = 2

        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager?.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    override fun navigation(fragment: Fragment, tag: String) {
        MyUtility.navigateTo(activity!!, fragment, tag, true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.search -> {
                homePresenterImp.redirectToSearchMovies(getString(R.string.search))
            }
        }
        return true
    }
}