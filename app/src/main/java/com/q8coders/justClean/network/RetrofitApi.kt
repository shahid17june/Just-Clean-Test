package com.q8coders.justClean.network

import com.q8coders.justClean.model.moviesModel.MoviesJsonModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Created by shahid on 8/26/2018.
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

}

