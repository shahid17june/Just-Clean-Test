package com.q8coders.justClean.base

import com.q8coders.justClean.application.MyApplication
import com.q8coders.justClean.network.RetrofitApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @Created by shahid on 8/26/2018.
 */
abstract class BaseService {

    protected fun<T> subscribe(observable: Observable<T>): Disposable {
        return observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError)
    }


    protected fun getRetrofit(): RetrofitApi = MyApplication.applicationComponent.getRetrofitApiService()

    private fun<T> onSuccess(response : T){
        successCallBack(response)
    }

    private fun onError(throwable: Throwable){
        onErrorCallBack(throwable)
    }

    abstract fun<T> successCallBack(response : T)
    abstract fun onErrorCallBack(throwable: Throwable)

}