package com.q8coders.justClean.di.components

import com.q8coders.justClean.di.modules.HomeModule
import com.q8coders.justClean.di.scope.PerFragment
import com.q8coders.justClean.screen.home.HomeFragment
import dagger.Component


/**
 * @Created by shahid on 8/26/2018.
 */
@PerFragment
@Component(modules = [(HomeModule::class)], dependencies = [(ApplicationComponent::class)])
interface HomeComponent {
    fun injectView(homeFragment: HomeFragment)
}

