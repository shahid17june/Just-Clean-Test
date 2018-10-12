package com.q8coders.justClean.base

import timber.log.Timber

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
class NotLoggingTree : Timber.Tree() {
    /*No need to print Log in release mode*/
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        return
    }
}