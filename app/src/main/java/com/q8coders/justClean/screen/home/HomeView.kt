package com.q8coders.justClean.screen.home

import android.support.v4.app.Fragment
import com.q8coders.justClean.base.BaseView


/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
interface HomeView : BaseView{
    fun navigation(fragment : Fragment, tag : String)

}