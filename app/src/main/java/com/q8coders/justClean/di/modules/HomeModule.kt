package com.q8coders.justClean.di.modules

import com.q8coders.justClean.di.scope.PerFragment
import com.q8coders.justClean.screen.home.HomePresenter
import com.q8coders.justClean.screen.home.HomePresenterImp
import com.q8coders.justClean.screen.home.HomeView
import dagger.Module
import dagger.Provides

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/

@Module
class HomeModule constructor(val mView: HomeView) {

    @Provides
    @PerFragment
    fun provideView(): HomeView = mView

    @Provides
    @PerFragment
    fun provideHomePresenter() : HomePresenter = HomePresenterImp()

}
