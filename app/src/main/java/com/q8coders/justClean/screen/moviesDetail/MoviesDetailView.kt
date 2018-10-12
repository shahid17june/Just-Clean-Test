package com.q8coders.justClean.screen.moviesDetail

import com.q8coders.justClean.base.BaseView
import com.q8coders.justClean.model.moviesModel.MoviesItem

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
interface MoviesDetailView : BaseView {
    fun setDetails(moviesItem: MoviesItem?)
    fun getObject(): MoviesItem?
}