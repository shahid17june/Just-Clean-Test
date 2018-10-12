package com.q8coders.justClean.screen.moviesDetail

import com.q8coders.justClean.base.BasePresenter
import com.q8coders.justClean.model.moviesModel.MoviesItem
import javax.inject.Inject

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
class MoviesDetailPresenterImp @Inject constructor(): BasePresenter<MoviesDetailView>(), MoviesDetailPresenter {

    private var moviesItem : MoviesItem?= null

    override fun init(){
        moviesItem = mView.getObject()

        mView.setDetails(moviesItem)
    }
}