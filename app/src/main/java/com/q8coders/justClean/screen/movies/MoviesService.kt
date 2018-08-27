package com.q8coders.justClean.screen.movies


import com.q8coders.justClean.base.BaseService
import com.q8coders.justClean.base.ServiceCallBack
import io.reactivex.disposables.Disposable

/**
 * @Created by shahid on 8/26/2018.
 */
class MoviesService(private val callBack: ServiceCallBack) : BaseService() {

    /* Make api call to get movies listing */
    fun getMoviesList(param: String, api_key: String, language: String, page: Int): Disposable? {
        callBack.onPrepare()
        return subscribe(getRetrofit().getMovies(param, api_key, language, page))
    }


    /* Make api call to search movies listing */
    fun searchMoviesList(api_key: String, language: String, query: String, page: Int): Disposable? {
        callBack.onPrepare()
        return subscribe(getRetrofit().searchMovies( api_key, language, query, page))
    }

    override fun <T> successCallBack(response: T) {
        callBack.onSuccess(response)
    }


    override fun onErrorCallBack(throwable: Throwable) {
        callBack.onError(throwable)
    }
}