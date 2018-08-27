package com.q8coders.justClean.screen.searchMovies

import android.support.v4.app.Fragment
import com.q8coders.justClean.base.BaseView
import com.q8coders.justClean.screen.movies.MoviesAdapter

/**
 * @Created by shahid on 8/27/2018.
 */
interface SearchMoviesView : BaseView {
    fun disableSwipeRefresh()
    fun setMoviesAdapter(moviesAdapter : MoviesAdapter)
    fun showHideProgress(visibility : Int)
    fun showHideLazyLoader(visibility: Int)
    fun errorMessage(message : String)
    fun navigation(fragment : Fragment, tag : String)
    fun setPlaceHolder(visibility: Int)
    fun getHMoviesAdapter() : MoviesAdapter
    fun getLocaleString(stringId : Int) : String
    fun getParam(): String
    fun imageClicked(imageUrl: String)
    fun hideKeyBoard()
}