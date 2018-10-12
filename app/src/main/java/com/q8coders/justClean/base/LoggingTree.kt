package com.q8coders.justClean.base

import timber.log.Timber

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
class LoggingTree : Timber.DebugTree() {

    /*return log information in more detail when its in debug mode*/
    override fun createStackElementTag(element: StackTraceElement): String? {
        return String.format("C:%s:%s",
                super.createStackElementTag(element),
                element.lineNumber)
    }
}