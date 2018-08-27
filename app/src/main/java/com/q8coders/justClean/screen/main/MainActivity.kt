package com.q8coders.justClean.screen.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.q8coders.justClean.R
import com.q8coders.justClean.base.BaseActivity
import com.q8coders.justClean.base.BaseFragment
import com.q8coders.justClean.screen.common.CommonFragment
import com.q8coders.justClean.screen.home.HomeFragment
import com.q8coders.justClean.utility.Constants
import com.q8coders.justClean.utility.MyUtility
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.toolbar.*


/**
 * @Created by shahid on 8/26/2018.
 */
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var menu: Menu? = null
    private var v: View? = null
    private var bundle: Bundle? = null

    companion object {
        var INSTANCE : MainActivity? = null
    }

    override fun setLayoutResources(): Int = R.layout.main_activity

    override fun onViewCreated() {
        setToolBar()
        INSTANCE = this

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.isDrawerIndicatorEnabled = false
        toggle.setHomeAsUpIndicator(R.drawable.hamburger_icon)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


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
            // val fragment = supportFragmentManager.findFragmentById(R.id.content)
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
            R.id.nav_popular_movies-> {
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

    fun getLocaleString(stringId : Int): String{
        return getString(stringId)
    }

    fun toolbarVisibility(isTransparent: Boolean) {
        if (isTransparent) {
            toolbar.background = ResourcesCompat.getDrawable(resources, R.color.transparent, null)
            appBarLayout.background =ResourcesCompat.getDrawable(resources, R.color.transparent, null)
        } else {
            toolbar.background = ResourcesCompat.getDrawable(resources, R.color.colorPrimary, null)
            appBarLayout.background = ResourcesCompat.getDrawable(resources, R.color.colorPrimary, null)

        }
    }






}