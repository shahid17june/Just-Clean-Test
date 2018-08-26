package com.q8coders.justClean.base

/**
 * @Created by shahid on 8/26/2018.
 */
interface ServiceCallBack {
    fun onPrepare()
    fun<T> onSuccess(response : T)
    fun onError(throwable: Throwable)
}