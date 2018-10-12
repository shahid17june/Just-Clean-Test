package com.q8coders.justClean.screen.home

import android.os.Bundle
import com.q8coders.justClean.base.BasePresenter
import com.q8coders.justClean.screen.searchMovies.SearchMoviesFragment
import com.q8coders.justClean.utility.Constants
import javax.inject.Inject


/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
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