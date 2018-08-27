package com.q8coders.justClean.di.modules

import com.q8coders.justClean.di.scope.PerFragment
import com.q8coders.justClean.screen.moviesDetail.MoviesDetailPresenter
import com.q8coders.justClean.screen.moviesDetail.MoviesDetailPresenterImp
import com.q8coders.justClean.screen.moviesDetail.MoviesDetailView
import dagger.Module
import dagger.Provides

/**
 * @Created by shahid on 8/27/2018.
 */
@Module
class MoviesDetailModule constructor(val mView: MoviesDetailView) {

    @Provides
    @PerFragment
    fun provideView(): MoviesDetailView = mView

    @Provides
    @PerFragment
    fun provideMoviesDetailPresenter() : MoviesDetailPresenter = MoviesDetailPresenterImp()

}
