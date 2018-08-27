package com.q8coders.justClean.screen.movies

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.PagerSnapHelper
import android.widget.TextView
import com.q8coders.justClean.R
import com.q8coders.justClean.application.MyApplication
import com.q8coders.justClean.base.BaseFragment
import com.q8coders.justClean.di.components.DaggerMoviesComponent
import com.q8coders.justClean.di.modules.MoviesModule
import com.q8coders.justClean.utility.Constants
import com.q8coders.justClean.utility.MyUtility
import com.q8coders.justClean.utility.ZoomInZoomOutImageDialog
import kotlinx.android.synthetic.main.common_recylerview_fragment.*
import kotlinx.android.synthetic.main.lazy_loading_indicator.*
import timber.log.Timber
import javax.inject.Inject

/**
 * @Created by shahid on 8/26/2018.
 */

class MoviesFragment : BaseFragment() , MoviesView{

    private var moviesAdapter: MoviesAdapter? = null
    private lateinit var myLayoutManager: LinearLayoutManager
    @Inject lateinit var moviesPresenterImp : MoviesPresenterImp

    override fun setLayoutResource(): Int = R.layout.common_recylerview_fragment


    override fun viewIsReady() {
       Timber.d("Tab is==============================================: $text")
        placeHolder?.text = getString(R.string.no_movies_found)
        myLayoutManager = LinearLayoutManager(activity, OrientationHelper.HORIZONTAL, false)
        commonRecyclerView?.layoutManager = myLayoutManager

        moviesPresenterImp.init()
        moviesPresenterImp.setUpRecyclerView()

    }

    override fun setFragmentTitle(actionBarTitle: TextView?, text: String?) {
       actionBarTitle?.text = Constants.EMPTY
    }

    override fun resolveDependency() {
       DaggerMoviesComponent.builder()
               .applicationComponent(MyApplication.applicationComponent)
               .moviesModule(MoviesModule(this))
               .build().injectView(this)
    }

    override fun retry() {
        moviesPresenterImp.resetValueForRetry()
        moviesPresenterImp.makeServiceCall()
    }

    override fun setMoviesAdapter(moviesAdapter: MoviesAdapter) {
        commonRecyclerView?.adapter = moviesAdapter
    }

    override fun getMoviesAdapter(): MoviesAdapter {
        if (moviesAdapter == null){
            moviesAdapter = MoviesAdapter(false, commonRecyclerView, myLayoutManager)

            /*PageSnap Helper class will be helped to scroll recycler view as a viewpager*/
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(commonRecyclerView)

        }


        return moviesAdapter!!
    }

    override fun disableSwipeRefresh() {
        swipeToRefresh?.isRefreshing = false
        swipeToRefresh?.isEnabled = false
    }

    override fun showHideProgress(visibility: Int) {
        super.showHideLoader(visibility)
    }

    override fun showHideLazyLoader(visibility: Int) {
        lazy_indicator?.visibility = visibility
    }

    override fun errorMessage(message: String) {
        showErrorMessageDialog(getString(R.string.error), message, true)
    }

    override fun navigation(fragment: Fragment, tag: String) {
        MyUtility.navigateTo(activity!!, fragment, tag, true)
    }

    override fun setPlaceHolder(visibility: Int) {
        placeHolder?.visibility = visibility
    }

    override fun getLocaleString(stringId: Int): String = getString(stringId)

    override fun getParam(): String {
        return when (text) {
            getString(R.string.popular_movies) -> Constants.POPULAR
            getString(R.string.top_rated) -> Constants.TOP_RATED
            else -> Constants.UP_COMING
        }

    }

    override fun imageClicked(imageUrl: String) {
        ZoomInZoomOutImageDialog(activity!!, imageUrl)
    }





}