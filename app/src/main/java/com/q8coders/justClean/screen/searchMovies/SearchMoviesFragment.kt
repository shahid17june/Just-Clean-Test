package com.q8coders.justClean.screen.searchMovies

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.q8coders.justClean.R
import com.q8coders.justClean.application.MyApplication
import com.q8coders.justClean.base.BaseFragment
import com.q8coders.justClean.di.components.DaggerSearchMoviesComponent
import com.q8coders.justClean.di.modules.SearchMoviesModule
import com.q8coders.justClean.screen.movies.MyMoviesAdapter
import com.q8coders.justClean.utility.MyUtility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.search_movies_fragment.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
class SearchMoviesFragment : BaseFragment(), SearchMoviesView {

    private var moviesAdapter: MyMoviesAdapter? = null
    @Inject lateinit var searchMoviesPresenterImp: SearchMoviesPresenterImp

    override fun setLayoutResource(): Int = R.layout.search_movies_fragment

    override fun viewIsReady() {
        moviesRecyclerView?.layoutManager = LinearLayoutManager(activity)
        moviesAdapter = MyMoviesAdapter(true)

        searchMoviesPresenterImp.init()
        searchMoviesPresenterImp.setUpRecyclerView()
    }

    override fun setFragmentTitle(actionBarTitle: TextView?, text: String?) {
        actionBarTitle?.text = text

        compositeDisposable.add(
                /* Analyze the text changes on edit box and after that make api call */
                RxTextView.textChanges(searchView)
                        .skip(1)
                        .subscribeOn(Schedulers.io())
                        .debounce(700, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .map { myText -> searchMoviesPresenterImp.makeServiceCall(text = myText.toString()) }
                        .subscribe()
        )
    }

    override fun onPause() {
        super.onPause()
        searchMoviesPresenterImp.disposeApiCall()
    }

    override fun resolveDependency() {
        DaggerSearchMoviesComponent.builder()
                .applicationComponent(MyApplication.applicationComponent)
                .searchMoviesModule(SearchMoviesModule(this))
                .build().injectView(this)
    }

    override fun retry() {
        searchMoviesPresenterImp.makeServiceCall(searchView?.text?.toString())
    }

    override fun setMoviesAdapter(moviesAdapter: MyMoviesAdapter) {
        moviesRecyclerView?.adapter = moviesAdapter
    }

    override fun getHMoviesAdapter(): MyMoviesAdapter {
        return moviesAdapter!!
    }


    override fun showHideProgress(visibility: Int) {
        super.showHideLoader(visibility)
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


    override fun hideKeyBoard(){
        MyUtility.hideSoftKeyBoard(activity)
    }
}