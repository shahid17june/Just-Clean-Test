package com.q8coders.justClean.utility

import android.app.Activity
import android.support.v7.app.AlertDialog
import com.q8coders.justClean.R

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
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
