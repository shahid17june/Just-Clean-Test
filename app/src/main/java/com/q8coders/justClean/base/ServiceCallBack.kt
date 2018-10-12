package com.q8coders.justClean.base

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
interface ServiceCallBack {
    fun onPrepare()
    fun<T> onSuccess(response : T)
    fun onError(throwable: Throwable)
}