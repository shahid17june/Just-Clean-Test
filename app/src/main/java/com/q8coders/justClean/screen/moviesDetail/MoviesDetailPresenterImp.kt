package com.q8coders.justClean.screen.moviesDetail

import com.q8coders.justClean.base.BasePresenter
import com.q8coders.justClean.model.moviesModel.MoviesItem
import javax.inject.Inject

/**
 * @Created by shahid on 8/27/2018.
 */
class MoviesDetailPresenterImp @Inject constructor(): BasePresenter<MoviesDetailView>(), MoviesDetailPresenter {

    private var moviesItem : MoviesItem?= null

    override fun init(){
        moviesItem = mView.getObject()

        mView.setDetails(moviesItem)
    }
}