package com.q8coders.justClean.network

import com.q8coders.justClean.model.generes.GeneresJsonModel
import com.q8coders.justClean.model.moviesModel.MoviesJsonModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
interface RetrofitApi {

    /*get Popular Movies*/
    @GET("movie/{param}")
    fun getMovies(@Path("param") param: String,
                  @Query("api_key") api_key: String,
                  @Query("language") language: String,
                  @Query("page") page: Int): Observable<MoviesJsonModel>


    /* Search  movies*/
    @GET("search/movie")
    fun searchMovies(@Query("api_key") api_key: String,
                     @Query("language") language: String,
                     @Query("query") query: String,
                     @Query("page") page: Int): Observable<MoviesJsonModel>

    /*get generes list*/
    @GET("genre/movie/list")
    fun getGenresList(@Query("api_key") api_key: String,
                   @Query("language") language: String): Observable<GeneresJsonModel>

}

