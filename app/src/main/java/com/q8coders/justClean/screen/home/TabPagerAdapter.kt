package com.q8coders.justClean.screen.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.q8coders.justClean.screen.common.CommonFragment
import com.q8coders.justClean.screen.movies.MoviesFragment
import com.q8coders.justClean.utility.Constants

/**
 * @Created by shahid on 8/26/2018.
 */
class TabPagerAdapter(fm: FragmentManager, private val numOfTabs: Int, private val title : Array<String>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        val bundle = Bundle()
        val mFragment  = MoviesFragment()
        bundle.putString(Constants.COMING_FROM, title[position])
        mFragment.arguments = bundle

        return mFragment
    }

    override fun getCount(): Int {
        return numOfTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }


}