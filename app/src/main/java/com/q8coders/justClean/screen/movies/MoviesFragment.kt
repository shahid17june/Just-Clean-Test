package com.q8coders.justClean.screen.movies

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.q8coders.justClean.R
import com.q8coders.justClean.application.MyApplication
import com.q8coders.justClean.base.BaseFragment
import com.q8coders.justClean.di.components.DaggerMoviesComponent
import com.q8coders.justClean.di.modules.MoviesModule
import com.q8coders.justClean.model.moviesModel.MoviesItem
import com.q8coders.justClean.utility.Constants
import com.q8coders.justClean.utility.DateUtility
import com.q8coders.justClean.utility.MyUtility
import com.yarolegovich.discretescrollview.DSVOrientation
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.movies_list_fragment.*
import timber.log.Timber
import javax.inject.Inject


/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
class MoviesFragment : BaseFragment(), MoviesView, DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder> {

    private var moviesAdapter: MyMoviesAdapter? = null
    @Inject lateinit var moviesPresenterImp: MoviesPresenterImp

    override fun setLayoutResource(): Int = R.layout.movies_list_fragment

    override fun viewIsReady() {
        Timber.d("Tab is==============================================: $text")

        moviesRecyclerView?.setOrientation(DSVOrientation.HORIZONTAL)
        moviesRecyclerView?.addOnItemChangedListener(this)

        moviesAdapter = MyMoviesAdapter(false)
        moviesRecyclerView?.adapter = moviesAdapter

        moviesRecyclerView?.setItemTransitionTimeMillis(150)
        moviesRecyclerView?.setItemTransformer(ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build())

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

    override fun setMoviesAdapter(moviesAdapter: MyMoviesAdapter) {
        moviesRecyclerView?.adapter = moviesAdapter
    }

    override fun getMoviesAdapter(): MyMoviesAdapter {
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

    override fun getParam(): String {
        return when (text) {
            getString(R.string.popular_movies) -> Constants.POPULAR
            getString(R.string.top_rated) -> Constants.TOP_RATED
            else -> Constants.UP_COMING
        }

    }


    override fun onCurrentItemChanged(viewHolder: RecyclerView.ViewHolder?, adapterPosition: Int) {
        onItemChanged(moviesAdapter?.getItem(adapterPosition))
    }

    override fun onItemChanged(moviesItem: MoviesItem?) {
        if(moviesItem!= null){

            moviesDuration?.text = GenresUtil.getGenresText(moviesItem.genreIds)

            buy.visibility = View.VISIBLE
            moviesTitle?.text = moviesItem.originalTitle

            if(!TextUtils.isEmpty(moviesItem.releaseDate))
                moviesReleaseDate?.text = getString(R.string.release_date).plus(DateUtility.getDayNameAndMonthNameFormat(moviesItem.releaseDate!!))

        }else{
            buy.visibility = View.GONE
        }

    }


}