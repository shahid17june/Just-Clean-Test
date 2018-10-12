package com.q8coders.justClean.screen.searchMovies

import android.support.v4.app.Fragment
import com.q8coders.justClean.base.BaseView
import com.q8coders.justClean.screen.movies.MyMoviesAdapter

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
interface SearchMoviesView : BaseView {
    fun setMoviesAdapter(moviesAdapter : MyMoviesAdapter)
    fun showHideProgress(visibility : Int)
    fun errorMessage(message : String)
    fun navigation(fragment : Fragment, tag : String)
    fun setPlaceHolder(visibility: Int)
    fun getHMoviesAdapter() : MyMoviesAdapter
    fun getLocaleString(stringId : Int) : String
    fun hideKeyBoard()
}