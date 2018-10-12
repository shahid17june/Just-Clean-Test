package com.q8coders.justClean.screen.movies

import com.q8coders.justClean.R
import com.q8coders.justClean.application.MyApplication
import com.q8coders.justClean.screen.main.MainActivity
import com.q8coders.justClean.utility.Constants
import timber.log.Timber
import java.lang.StringBuilder
/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
class GenresUtil {

    companion object {
        private var genres : StringBuilder? = null

        fun getGenresText(genreIds: MutableList<Int>?): String?{
            genres = StringBuilder(Constants.EMPTY)

            MyApplication.generesJsonModel?.genres
                    ?.filter { genreIds?.contains(it?.id)!! }
                    ?.forEach { genres?.append(it?.name)?.append(", ") }

          val genText = if(genres != null && genres?.length!! > 2){
              Timber.d("=============================================")
              genres?.substring(0, genres?.length?.minus(2)!!).plus(" ")
                      .plus(MainActivity.INSTANCE?.getLocaleString(R.string.film))
           }else{
              genres?.toString()
          }
            return genText
        }
    }


}