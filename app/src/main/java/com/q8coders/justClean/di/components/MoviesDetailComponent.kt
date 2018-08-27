package com.q8coders.justClean.di.components

import com.q8coders.justClean.di.modules.MoviesDetailModule
import com.q8coders.justClean.di.scope.PerFragment
import com.q8coders.justClean.screen.moviesDetail.MoviesDetailFragment
import dagger.Component


/**
 * @Created by shahid on 8/27/2018.
 */
@PerFragment
@Component(modules = [(MoviesDetailModule::class)], dependencies = [(ApplicationComponent::class)])
interface MoviesDetailComponent {
    fun injectView(moviesDetailFragment: MoviesDetailFragment)
}

