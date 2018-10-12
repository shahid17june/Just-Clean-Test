package com.q8coders.justClean.application

import android.app.Application
import com.q8coders.justClean.BuildConfig
import com.q8coders.justClean.base.LoggingTree
import com.q8coders.justClean.di.components.ApplicationComponent
import com.q8coders.justClean.di.components.DaggerApplicationComponent
import com.q8coders.justClean.di.modules.ApplicationModule
import timber.log.Timber
import com.q8coders.justClean.base.NotLoggingTree
import android.support.v7.app.AppCompatDelegate
import com.q8coders.justClean.model.generes.GeneresJsonModel


/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
class MyApplication : Application() {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate() {
        super.onCreate()

        // print Log only in debug mode
        if (BuildConfig.DEBUG) {
            Timber.plant(LoggingTree())
        }else{
            Timber.plant(NotLoggingTree())
        }

        resolveDependency()
    }

    private fun resolveDependency() {
        applicationComponent =
                DaggerApplicationComponent.builder()
                        .applicationModule(ApplicationModule(this))
                        .build()
    }

    companion object {
        lateinit var applicationComponent: ApplicationComponent
        var generesJsonModel : GeneresJsonModel?= null

    }


}