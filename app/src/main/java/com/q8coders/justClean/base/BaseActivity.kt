package com.q8coders.justClean.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.q8coders.justClean.R
import com.q8coders.justClean.utility.MyMessageDialog
import kotlinx.android.synthetic.main.toolbar.*


/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(setLayoutResources())
        resolveDependencyInjection()
        onViewCreated()
    }

    protected fun setToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    protected fun showDialogBox(message: Int) {
        object : MyMessageDialog(this, getString(R.string.error), getString(message)) {
            override fun dialogPositiveClicked() {
                onBackPressed()
            }
        }
    }


    abstract fun setLayoutResources(): Int
    abstract fun onViewCreated()
    abstract fun resolveDependencyInjection()

}