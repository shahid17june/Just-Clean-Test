package com.q8coders.justClean.utility

import android.app.Activity
import android.support.v7.app.AlertDialog
import com.q8coders.justClean.R

/**
 * @Created by shahid on 8/26/2018.
 */
abstract class ConfirmationDialog constructor(activity :Activity, title: String, message: String, positive : String){

    init {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)

        builder.setPositiveButton(positive) { dialog, _ ->
            dialogPositiveClicked()
            dialog.dismiss()
        }

        builder.setNegativeButton(activity.getString(R.string.cancel)) { dialog, _ ->
            dialogNegativeClicked()
            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }

    abstract fun dialogPositiveClicked()
    abstract fun dialogNegativeClicked()


}
