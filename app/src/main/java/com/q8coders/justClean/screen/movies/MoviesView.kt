package com.q8coders.justClean.screen.movies

import android.support.v4.app.Fragment
import com.q8coders.justClean.base.BaseView


/**
 * @Created by shahid on 8/26/2018.
 */
interface MoviesView : BaseView{
    fun disableSwipeRefresh()
    fun setMoviesAdapter(moviesAdapter : MoviesAdapter)
    fun showHideProgress(visibility : Int)
    fun showHideLazyLoader(visibility: Int)
    fun errorMessage(message : String)
    fun navigation(fragment : Fragment, tag : String)
    fun setPlaceHolder(visibility: Int)
    fun getMoviesAdapter() : MoviesAdapter
    fun getLocaleString(stringId : Int) : String
    fun getParam(): String
    fun imageClicked(imageUrl: String)
}