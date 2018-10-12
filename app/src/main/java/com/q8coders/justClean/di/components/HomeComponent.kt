package com.q8coders.justClean.di.components

import com.q8coders.justClean.di.modules.HomeModule
import com.q8coders.justClean.di.scope.PerFragment
import com.q8coders.justClean.screen.home.HomeFragment
import dagger.Component


/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
@PerFragment
@Component(modules = [(HomeModule::class)], dependencies = [(ApplicationComponent::class)])
interface HomeComponent {
    fun injectView(homeFragment: HomeFragment)
}

