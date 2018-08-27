package com.q8coders.justClean.di.modules

import com.q8coders.justClean.di.scope.PerFragment
import com.q8coders.justClean.screen.searchMovies.SearchMoviesPresenter
import com.q8coders.justClean.screen.searchMovies.SearchMoviesPresenterImp
import com.q8coders.justClean.screen.searchMovies.SearchMoviesView
import dagger.Module
import dagger.Provides

/**
 * @Created by shahid on 8/27/2018.
 */

@Module
class SearchMoviesModule constructor(val mView: SearchMoviesView) {

    @Provides
    @PerFragment
    fun provideView(): SearchMoviesView = mView

    @Provides
    @PerFragment
    fun provideSearchMoviesPresenter() : SearchMoviesPresenter = SearchMoviesPresenterImp()

}
