package com.q8coders.justClean.di.components

import com.q8coders.justClean.di.modules.MoviesDetailModule
import com.q8coders.justClean.di.scope.PerFragment
import com.q8coders.justClean.screen.moviesDetail.MoviesDetailFragment
import dagger.Component


/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
@PerFragment
@Component(modules = [(MoviesDetailModule::class)], dependencies = [(ApplicationComponent::class)])
interface MoviesDetailComponent {
    fun injectView(moviesDetailFragment: MoviesDetailFragment)
}

