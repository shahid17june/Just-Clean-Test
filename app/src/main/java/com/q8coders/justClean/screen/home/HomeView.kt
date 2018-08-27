package com.q8coders.justClean.screen.home

import android.support.v4.app.Fragment
import com.q8coders.justClean.base.BaseView


/**
 * @Created by shahid on 8/26/2018.
 */
interface HomeView : BaseView{
    fun navigation(fragment : Fragment, tag : String)

}