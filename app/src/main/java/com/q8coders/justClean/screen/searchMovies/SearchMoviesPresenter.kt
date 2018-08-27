package com.q8coders.justClean.screen.searchMovies

/**
 * @Created by shahid on 8/27/2018.
 */
interface SearchMoviesPresenter {
    fun init()
    fun setUpRecyclerView()
    fun makeServiceCall(text : String?)
    fun resetValueForRetry()
    fun redirectToSearchMovies()
}