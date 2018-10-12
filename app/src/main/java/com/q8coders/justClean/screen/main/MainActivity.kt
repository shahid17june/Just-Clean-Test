package com.q8coders.justClean.screen.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.q8coders.justClean.BuildConfig
import com.q8coders.justClean.R
import com.q8coders.justClean.application.MyApplication
import com.q8coders.justClean.base.BaseActivity
import com.q8coders.justClean.base.BaseFragment
import com.q8coders.justClean.base.ServiceCallBack
import com.q8coders.justClean.model.generes.GeneresJsonModel
import com.q8coders.justClean.screen.home.HomeFragment
import com.q8coders.justClean.screen.movies.MoviesService
import com.q8coders.justClean.utility.Constants
import com.q8coders.justClean.utility.MyUtility
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.toolbar.*

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, ServiceCallBack {


    private var menu: Menu? = null
    private var v: View? = null
    private var bundle: Bundle? = null
    private val moviesService: MoviesService = MoviesService(this)

    companion object {
        var INSTANCE: MainActivity? = null
    }

    override fun setLayoutResources(): Int = R.layout.main_activity

    override fun onViewCreated() {
        setToolBar()
        INSTANCE = this

        if (MyApplication.generesJsonModel == null) {
            moviesService.getGenresList(BuildConfig.ACCESS_KEY, Constants.LANGUAGE)
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.isDrawerIndicatorEnabled = false
        toggle.setHomeAsUpIndicator(R.drawable.hamburger_icon)
        toggle.syncState()

        //Make click on first navigation option
        val navigationView = nav_view as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        menu = navigationView.menu

        v = navigationView.getHeaderView(0)

        menu?.findItem(R.id.nav_popular_movies)?.isChecked = true
        onNavigationItemSelected(menu?.findItem(R.id.nav_popular_movies)!!)


        toggle.toolbarNavigationClickListener = View.OnClickListener {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                drawer_layout.openDrawer(GravityCompat.START)
            }

        }


        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {

                toggle.isDrawerIndicatorEnabled = false
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                toggle.setHomeAsUpIndicator(R.drawable.arrow_left)

                toolbar.setNavigationOnClickListener { _ ->
                    MyUtility.hideSoftKeyBoard(this)
                    onBackPressed()
                }

            } else {
                //show hamburger
                toggle.isDrawerIndicatorEnabled = false
                toggle.setHomeAsUpIndicator(R.drawable.hamburger_icon)
                toggle.syncState()
                toolbar.setNavigationOnClickListener { _ ->
                    MyUtility.hideSoftKeyBoard(this)
                    drawer_layout.openDrawer(GravityCompat.START)
                }
            }


        }

    }


    override fun resolveDependencyInjection() {

    }


    private fun getBundle(): Bundle? {
        if (bundle == null) {
            bundle = Bundle()
        }
        return bundle
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer_layout.closeDrawer(GravityCompat.START)

        var fragment: BaseFragment? = null
        val bundle = getBundle()
        var tag: String? = null

        when (item.itemId) {
            R.id.nav_popular_movies -> {
                tag = getString(R.string.popular_movies)
                fragment = HomeFragment()
                bundle?.putString(Constants.COMING_FROM, tag)
            }
        }

        if (fragment != null) {
            fragment.arguments = bundle
            MyUtility.navigateTo(this, fragment, tag, false)
        }
        return true
    }

    fun getLocaleString(stringId: Int): String {
        return getString(stringId)
    }

    fun toolbarVisibility(isTransparent: Boolean) {
        if (isTransparent) {
            toolbar.background = ResourcesCompat.getDrawable(resources, R.color.transparent, null)
        } else {
            toolbar.background = ResourcesCompat.getDrawable(resources, R.color.colorPrimary, null)
        }
    }

    override fun onPrepare() {

    }

    override fun <T> onSuccess(response: T) {
        if (response is GeneresJsonModel) {
            MyApplication.generesJsonModel = response
        }
    }

    override fun onError(throwable: Throwable) {

    }


}