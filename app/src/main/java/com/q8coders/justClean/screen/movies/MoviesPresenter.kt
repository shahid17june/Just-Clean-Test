package com.q8coders.justClean.screen.movies

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
interface MoviesPresenter {
    fun init()
    fun setUpRecyclerView()
    fun makeServiceCall()
    fun resetValueForRetry()

}