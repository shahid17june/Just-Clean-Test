package com.q8coders.justClean.screen.movies

/**
 * @Created by shahid on 8/26/2018.
 */
interface MoviesPresenter {
    fun init()
    fun setUpRecyclerView()
    fun makeServiceCall()
    fun resetValueForRetry()

}