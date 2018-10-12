package com.q8coders.justClean.di.modules

import com.q8coders.justClean.di.scope.PerFragment
import com.q8coders.justClean.screen.searchMovies.SearchMoviesPresenter
import com.q8coders.justClean.screen.searchMovies.SearchMoviesPresenterImp
import com.q8coders.justClean.screen.searchMovies.SearchMoviesView
import dagger.Module
import dagger.Provides

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
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
