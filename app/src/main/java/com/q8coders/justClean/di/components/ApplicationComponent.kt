package com.q8coders.justClean.di.components

import com.google.gson.Gson
import com.q8coders.justClean.application.MyApplication
import com.q8coders.justClean.di.modules.ApplicationModule
import com.q8coders.justClean.network.RetrofitApi
import dagger.Component
import javax.inject.Singleton

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent{

    fun getContext() : MyApplication

    fun getRetrofitApiService() : RetrofitApi

    fun getGsonObject() :Gson

}