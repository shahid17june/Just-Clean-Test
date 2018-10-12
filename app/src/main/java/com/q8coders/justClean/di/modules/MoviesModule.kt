package com.q8coders.justClean.di.modules

import com.q8coders.justClean.di.scope.PerFragment
import com.q8coders.justClean.screen.home.HomePresenter
import com.q8coders.justClean.screen.home.HomePresenterImp
import com.q8coders.justClean.screen.home.HomeView
import com.q8coders.justClean.screen.movies.MoviesPresenter
import com.q8coders.justClean.screen.movies.MoviesPresenterImp
import com.q8coders.justClean.screen.movies.MoviesView
import dagger.Module
import dagger.Provides

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
@Module
class MoviesModule constructor(val mView: MoviesView) {

    @Provides
    @PerFragment
    fun provideView(): MoviesView = mView

    @Provides
    @PerFragment
    fun provideMoviesPresenter() : MoviesPresenter = MoviesPresenterImp()

}
