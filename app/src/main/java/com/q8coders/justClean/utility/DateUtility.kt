package com.q8coders.justClean.utility

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Created by shahid on 6/30/2018.
 */
class DateUtility {

    companion object {
        private var inFormatDate : SimpleDateFormat? = null
        private var outPutFormat : SimpleDateFormat? = null


        fun getDayNameAndMonthNameFormat(input: String): String {

            val inFormat = getInFormat()
            var date: Date? = null
            try {
                date = inFormat?.parse(input)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            val outFormat = getOutFormat()
            val outputDate = outFormat?.format(date)
            Timber.d(outputDate)

            return outputDate!!
        }

        private fun getInFormat(): SimpleDateFormat?{
            if(inFormatDate==null){
                inFormatDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            }
            return inFormatDate
        }

        private fun getOutFormat(): SimpleDateFormat?{
            if(outPutFormat==null){
                outPutFormat = SimpleDateFormat( " dd MMM yyyy", Locale.ENGLISH)
            }
            return outPutFormat
        }



    }
}