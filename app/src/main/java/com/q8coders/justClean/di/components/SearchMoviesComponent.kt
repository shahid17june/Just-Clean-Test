package com.q8coders.justClean.di.components

import com.q8coders.justClean.di.modules.SearchMoviesModule
import com.q8coders.justClean.di.scope.PerFragment
import com.q8coders.justClean.screen.searchMovies.SearchMoviesFragment
import dagger.Component


/**
 * @Created by shahid on 8/27/2018.
 */
@PerFragment
@Component(modules = [(SearchMoviesModule::class)], dependencies = [(ApplicationComponent::class)])
interface SearchMoviesComponent {
    fun injectView(searchMoviesFragment: SearchMoviesFragment)
}

