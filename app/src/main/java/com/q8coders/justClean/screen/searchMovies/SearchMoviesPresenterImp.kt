package com.q8coders.justClean.screen.searchMovies

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.q8coders.justClean.BuildConfig
import com.q8coders.justClean.R
import com.q8coders.justClean.base.BasePresenter
import com.q8coders.justClean.base.ServiceCallBack
import com.q8coders.justClean.model.moviesModel.MoviesJsonModel
import com.q8coders.justClean.screen.movies.MoviesAdapter
import com.q8coders.justClean.screen.movies.MoviesService
import com.q8coders.justClean.screen.moviesDetail.MoviesDetailFragment
import com.q8coders.justClean.utility.Constants
import com.q8coders.justClean.utility.RecyclerClickListner
import javax.inject.Inject

/**
 * @Created by shahid on 8/27/2018.
 */
class SearchMoviesPresenterImp @Inject constructor(): BasePresenter<SearchMoviesView>(), SearchMoviesPresenter ,ServiceCallBack, RecyclerClickListner{

    private var moviesService: MoviesService? = null
    private var page = 1
    private lateinit var moviesAdapter: MoviesAdapter

    override fun init() {
        moviesService = MoviesService(this)
    }

    override fun setUpRecyclerView() {
        moviesAdapter = mView.getHMoviesAdapter()
        moviesAdapter.setOnClickListner(this)
        mView.setMoviesAdapter(moviesAdapter)
        mView.disableSwipeRefresh()

    }

    override fun makeServiceCall(text : String?) {
        if(!TextUtils.isEmpty(text)){
            moviesService?.searchMoviesList(BuildConfig.ACCESS_KEY, Constants.LANGUAGE, text!!,page)
        }

    }

    override fun onPrepare() {
        mView.hideKeyBoard()
        mView.showHideProgress(View.VISIBLE)
    }

    override fun <T> onSuccess(response: T) {
        mView.showHideProgress(View.GONE)

        if (response is MoviesJsonModel) {
            moviesAdapter.clearAllData()
            moviesAdapter.addDataAsBulkList(response.results!!)

        }

        if (moviesAdapter.itemCount == 0) {
            mView.setPlaceHolder(View.VISIBLE)
        } else {
            mView.setPlaceHolder(View.GONE)
        }


    }

    override fun onError(throwable: Throwable) {
        mView.showHideProgress(View.GONE)
        mView.errorMessage(handelApiError(throwable))

    }

    override fun resetValueForRetry() {
        page = 1
    }

    override fun itemClicked(position: Int, taskIdentifier: String?) {
        when(taskIdentifier){
            Constants.ITEM_CLICKED->{
                val tag = mView.getLocaleString(R.string.OK)
                val mFragment = MoviesDetailFragment()
                val bundle = Bundle()
                bundle.putParcelable(Constants.OBJECT, moviesAdapter.getItem(position))
                mFragment.arguments = bundle
                mView.navigation(mFragment, tag)
            }
            else->{
                mView.imageClicked(taskIdentifier!!)
            }
        }
    }

    override fun redirectToSearchMovies(){
        val tag = mView.getLocaleString(R.string.search)
        val mFragment = SearchMoviesFragment()
        val bundle = Bundle()
        bundle.putString(Constants.OBJECT, tag)
        mFragment.arguments = bundle
        mView.navigation(mFragment, tag)

    }
}