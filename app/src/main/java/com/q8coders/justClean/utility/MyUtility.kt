package com.q8coders.justClean.utility

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.view.inputmethod.InputMethodManager
import com.q8coders.justClean.R


/**
 * @Created by shahid on 8/26/2018.
 */
class MyUtility {

    companion object {

        fun navigateTo(activity: FragmentActivity, mFragment: Fragment, TAG: String?, isBackStack: Boolean) {
            val fragmentManager = activity.supportFragmentManager.beginTransaction()
            //enter, exit, pop-enter, pop-exit
            //fragmentManager.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
            fragmentManager.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

            if (isBackStack)
                fragmentManager.addToBackStack(TAG)

            fragmentManager.replace(R.id.content, mFragment, TAG).commitAllowingStateLoss()
        }


        fun hideSoftKeyBoard(activity: FragmentActivity?) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            if (imm.isAcceptingText && activity.currentFocus != null) { // verify if the soft keyboard is open
                imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
            }
        }


    }
}