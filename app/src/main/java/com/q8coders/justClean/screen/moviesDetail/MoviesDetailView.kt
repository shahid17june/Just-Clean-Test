package com.q8coders.justClean.screen.moviesDetail

import com.q8coders.justClean.base.BaseView
import com.q8coders.justClean.model.moviesModel.MoviesItem

/**
 * @Created by shahid on 8/27/2018.
 */
interface MoviesDetailView : BaseView {
    fun setDetails(moviesItem: MoviesItem?)
    fun getObject(): MoviesItem?
}