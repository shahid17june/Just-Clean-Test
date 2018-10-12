package com.q8coders.justClean.di.components

import com.q8coders.justClean.di.modules.SearchMoviesModule
import com.q8coders.justClean.di.scope.PerFragment
import com.q8coders.justClean.screen.searchMovies.SearchMoviesFragment
import dagger.Component


/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
@PerFragment
@Component(modules = [(SearchMoviesModule::class)], dependencies = [(ApplicationComponent::class)])
interface SearchMoviesComponent {
    fun injectView(searchMoviesFragment: SearchMoviesFragment)
}

