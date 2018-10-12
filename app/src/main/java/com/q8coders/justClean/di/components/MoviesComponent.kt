package com.q8coders.justClean.di.components

import com.q8coders.justClean.di.modules.HomeModule
import com.q8coders.justClean.di.modules.MoviesModule
import com.q8coders.justClean.di.scope.PerFragment
import com.q8coders.justClean.screen.home.HomeFragment
import com.q8coders.justClean.screen.movies.MoviesFragment
import dagger.Component


/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
@PerFragment
@Component(modules = [(MoviesModule::class)], dependencies = [(ApplicationComponent::class)])
interface MoviesComponent {
    fun injectView(moviesFragment: MoviesFragment)
}

