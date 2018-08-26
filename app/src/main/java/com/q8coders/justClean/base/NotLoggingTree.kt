package com.q8coders.justClean.base

import timber.log.Timber

/**
 * @Created by shahid on 8/26/2018.
 */
class NotLoggingTree : Timber.Tree() {
    /*No need to print Log in release mode*/
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        return
    }
}