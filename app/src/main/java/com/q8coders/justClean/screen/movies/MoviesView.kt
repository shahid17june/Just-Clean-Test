package com.q8coders.justClean.screen.movies

import android.support.v4.app.Fragment
import com.q8coders.justClean.base.BaseView
import com.q8coders.justClean.model.moviesModel.MoviesItem

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
interface MoviesView : BaseView{
    fun setMoviesAdapter(moviesAdapter : MyMoviesAdapter)
    fun showHideProgress(visibility : Int)
    fun errorMessage(message : String)
    fun navigation(fragment : Fragment, tag : String)
    fun setPlaceHolder(visibility: Int)
    fun getMoviesAdapter() : MyMoviesAdapter
    fun getLocaleString(stringId : Int) : String
    fun getParam(): String
    fun onItemChanged(moviesItem: MoviesItem?)
}