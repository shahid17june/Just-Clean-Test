package com.q8coders.justClean.screen.home

import android.os.Bundle
import com.q8coders.justClean.R
import com.q8coders.justClean.base.BasePresenter
import com.q8coders.justClean.screen.searchMovies.SearchMoviesFragment
import com.q8coders.justClean.utility.Constants
import javax.inject.Inject


/**
 * @Created by shahid on 8/26/2018.
 */
class HomePresenterImp @Inject constructor(): BasePresenter<HomeView>(), HomePresenter {

    override fun redirectToSearchMovies(tag : String){
        val mFragment = SearchMoviesFragment()
        val bundle = Bundle()
        bundle.putString(Constants.OBJECT, tag)
        mFragment.arguments = bundle
        mView.navigation(mFragment, tag)

    }
}