package com.q8coders.justClean.screen.searchMovies

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
interface SearchMoviesPresenter {
    fun init()
    fun setUpRecyclerView()
    fun makeServiceCall(text : String?)
}