package com.q8coders.justClean.screen.searchMovies

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.q8coders.justClean.R
import com.q8coders.justClean.application.MyApplication
import com.q8coders.justClean.base.BaseFragment
import com.q8coders.justClean.di.components.DaggerSearchMoviesComponent
import com.q8coders.justClean.di.modules.SearchMoviesModule
import com.q8coders.justClean.screen.movies.MoviesAdapter
import com.q8coders.justClean.utility.Constants
import com.q8coders.justClean.utility.MyUtility
import com.q8coders.justClean.utility.ZoomInZoomOutImageDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.common_recylerview_fragment.*
import kotlinx.android.synthetic.main.lazy_loading_indicator.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @Created by shahid on 8/27/2018.
 */
class SearchMoviesFragment : BaseFragment(), SearchMoviesView {

    private var moviesAdapter: MoviesAdapter? = null

    private lateinit var myLayoutManager: LinearLayoutManager

    @Inject lateinit var searchMoviesPresenterImp: SearchMoviesPresenterImp

    override fun setLayoutResource(): Int = R.layout.common_recylerview_fragment


    override fun viewIsReady() {
        searchView?.visibility = View.VISIBLE
        placeHolder?.text = getString(R.string.no_movies_found)
        myLayoutManager = LinearLayoutManager(activity)
        commonRecyclerView?.layoutManager = myLayoutManager

        searchMoviesPresenterImp.init()
        searchMoviesPresenterImp.setUpRecyclerView()

        /* Analyze the text changes on edit box and make api call */
        RxTextView.textChanges(searchView)
                .skip(1)
                .debounce(350, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map { text -> searchMoviesPresenterImp.makeServiceCall(text = text.toString()) }
                .subscribe()

    }

    override fun setFragmentTitle(actionBarTitle: TextView?, text: String?) {
        actionBarTitle?.text = text
    }

    override fun resolveDependency() {
        DaggerSearchMoviesComponent.builder()
                .applicationComponent(MyApplication.applicationComponent)
                .searchMoviesModule(SearchMoviesModule(this))
                .build().injectView(this)
    }

    override fun retry() {
        searchMoviesPresenterImp.resetValueForRetry()
        searchMoviesPresenterImp.makeServiceCall(searchView?.text?.toString())
    }

    override fun setMoviesAdapter(moviesAdapter: MoviesAdapter) {
        commonRecyclerView?.adapter = moviesAdapter
    }

    override fun getHMoviesAdapter(): MoviesAdapter {
        if (moviesAdapter == null){
            moviesAdapter = MoviesAdapter(true, commonRecyclerView, myLayoutManager)

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

    override fun hideKeyBoard(){
        MyUtility.hideSoftKeyBoard(activity)
    }
}