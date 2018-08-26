package com.q8coders.justClean.base

import timber.log.Timber

/**
 * @Created by shahid on 8/26/2018.
 */
class LoggingTree : Timber.DebugTree() {

    /*return log information in more detail when its in debug mode*/
    override fun createStackElementTag(element: StackTraceElement): String? {
        return String.format("C:%s:%s",
                super.createStackElementTag(element),
                element.lineNumber)
    }
}