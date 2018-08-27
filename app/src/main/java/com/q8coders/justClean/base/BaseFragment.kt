package com.q8coders.justClean.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.q8coders.justClean.R
import com.q8coders.justClean.utility.ConfirmationDialog
import com.q8coders.justClean.utility.Constants
import com.q8coders.justClean.utility.MyMessageDialog
import kotlinx.android.synthetic.main.progressbar.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber

/**
 * @Created by shahid on 8/26/2018.
 */
abstract class BaseFragment : Fragment(), BaseView {
    private var mView: View? = null
    private var isFragmentExists: Boolean = false
    private var tootBarTitle: TextView? = null
    protected var text: String? = Constants.EMPTY
    private var isViewDestroy: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tootBarTitle = activity?.toolBarTitle
        isFragmentExists = true

        val bundle = arguments
        text = bundle?.getString(Constants.COMING_FROM)
        setHasOptionsMenu(true)

        resolveDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mView == null) {
            mView = inflater.inflate(setLayoutResource(), container, false)
        }
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isFragmentExists) {
            isFragmentExists = false
            viewIsReady()
        }
    }

    override fun onResume() {
        super.onResume()
        setFragmentTitle(tootBarTitle, text)
    }


    protected fun showHideLoader(visibilty: Int) {
        when (visibilty) {
            View.GONE -> {
               avi?.smoothToHide()
            }
            View.VISIBLE -> {
               avi?.smoothToShow()
            }
        }
    }


    protected fun showErrorMessageDialog(title: String, errorMessage: String, isWithRetry: Boolean) {

        if (isWithRetry  && getString(R.string.security_patches_has_been_updated)!= errorMessage) {
            object : ConfirmationDialog(activity!!, title, errorMessage, getString(R.string.retry)) {
                override fun dialogNegativeClicked() {

                }

                override fun dialogPositiveClicked() {
                    retry()
                }
            }
        } else {
            object : MyMessageDialog(activity!!, title, errorMessage) {
                override fun dialogPositiveClicked() {

                }
            }
        }


    }

    override fun onDestroyView() {
        isViewDestroy = true
        super.onDestroyView()
        if (mView != null && mView?.parent != null) {
            try{
                val parentViewGroup: ViewGroup? = mView?.parent as ViewGroup?
                parentViewGroup?.removeAllViews()
            }catch (exception : Exception){
                Timber.e(exception.message)
            }

        }
    }

    protected fun showToastMessage(stringId: Int) {
        Toast.makeText(activity!!, getString(stringId), Toast.LENGTH_LONG).show()
    }


    override fun isViewDestroyed(): Boolean = isViewDestroy


    protected abstract fun setLayoutResource(): Int
    protected abstract fun viewIsReady()
    protected abstract fun setFragmentTitle(actionBarTitle: TextView?, text: String?)
    protected abstract fun resolveDependency()
    protected abstract fun retry()

}