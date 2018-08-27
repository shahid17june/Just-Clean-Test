package com.q8coders.justClean.screen.movies

import android.os.Bundle
import android.view.View
import com.q8coders.justClean.BuildConfig
import com.q8coders.justClean.R
import com.q8coders.justClean.base.BasePresenter
import com.q8coders.justClean.base.ServiceCallBack
import com.q8coders.justClean.model.moviesModel.MoviesJsonModel
import com.q8coders.justClean.screen.moviesDetail.MoviesDetailFragment
import com.q8coders.justClean.screen.searchMovies.SearchMoviesFragment
import com.q8coders.justClean.utility.Constants
import com.q8coders.justClean.utility.RecyclerClickListner
import javax.inject.Inject


/**
 * @Created by shahid on 8/26/2018.
 */
class MoviesPresenterImp @Inject constructor() : BasePresenter<MoviesView>(), MoviesPresenter, ServiceCallBack, RecyclerClickListner {


    private var moviesService: MoviesService? = null
    private var page = 0
    private lateinit var moviesAdapter: MoviesAdapter

    override fun init() {
        moviesService = MoviesService(this)
    }

    override fun setUpRecyclerView() {
        moviesAdapter = mView.getMoviesAdapter()
        moviesAdapter.setOnClickListner(this)
        mView.setMoviesAdapter(moviesAdapter)
        mView.disableSwipeRefresh()

        makeServiceCall()
    }

    override fun makeServiceCall() {
        moviesService?.getMoviesList(mView.getParam(), BuildConfig.ACCESS_KEY, Constants.LANGUAGE, ++page)
    }

    override fun onPrepare() {
        mView.showHideProgress(View.VISIBLE)
    }

    override fun <T> onSuccess(response: T) {
        mView.showHideProgress(View.GONE)

        if (response is MoviesJsonModel) {
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
        page = 0
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




}