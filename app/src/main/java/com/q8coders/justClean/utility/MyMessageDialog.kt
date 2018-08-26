package com.q8coders.justClean.utility

import android.app.Activity
import android.support.v7.app.AlertDialog
import com.q8coders.justClean.R

/**
 * @Created by shahid on 8/26/2018.
 */

abstract class MyMessageDialog constructor(activity :Activity,title: String , message: String){

    init {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)

        builder.setPositiveButton(activity.getString(R.string.OK)) { dialog, _ ->
            dialogPositiveClicked()
            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }

    abstract fun dialogPositiveClicked()


}
