package com.q8coders.justClean.utility

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.jakewharton.rxbinding2.view.RxView
import com.q8coders.justClean.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_zoom_in_zoom_out_dialog.*
import kotlinx.android.synthetic.main.progressbar.*

/**
 * @Created by shahid on 8/26/2018.
 */
class ZoomInZoomOutImageDialog constructor(activity: Activity, src: String) {

    init {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.image_zoom_in_zoom_out_dialog)

        val lp = WindowManager.LayoutParams()
        val window = dialog.window
        lp.copyFrom(window!!.attributes)
//This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp

        val myZoomageView = dialog.myZoomageView
        val aviIndicator = dialog.avi

        RxView.clicks(dialog.cancelButton).subscribe { dialog.dismiss() }

        aviIndicator?.visibility = View.VISIBLE
        Picasso.get().load(src).into(myZoomageView, object : Callback {
            override fun onSuccess() {
                aviIndicator?.visibility = View.GONE
            }

            override fun onError(e : Exception) {
                aviIndicator?.visibility = View.GONE
            }

        })
        dialog.show()

    }
}