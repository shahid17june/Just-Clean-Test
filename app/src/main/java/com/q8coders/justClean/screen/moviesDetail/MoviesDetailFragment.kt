package com.q8coders.justClean.screen.moviesDetail

import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.q8coders.justClean.BuildConfig
import com.q8coders.justClean.R
import com.q8coders.justClean.application.MyApplication
import com.q8coders.justClean.base.BaseFragment
import com.q8coders.justClean.di.components.DaggerMoviesDetailComponent
import com.q8coders.justClean.di.modules.MoviesDetailModule
import com.q8coders.justClean.model.moviesModel.MoviesItem
import com.q8coders.justClean.screen.main.MainActivity
import com.q8coders.justClean.utility.Constants
import com.q8coders.justClean.utility.DateUtility
import com.q8coders.justClean.utility.ZoomInZoomOutImageDialog
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_detail_content.*
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

/**
 * @Created by shahid on 8/27/2018.
 */
class MoviesDetailFragment : BaseFragment(), MoviesDetailView {

    @Inject lateinit var moviesDetailPresenterImp: MoviesDetailPresenterImp

    override fun setLayoutResource(): Int = R.layout.movies_detail_fragment


    override fun viewIsReady() {
        moviesDetailPresenterImp.init()
    }

    override fun setFragmentTitle(actionBarTitle: TextView?, text: String?) {
        actionBarTitle?.text =Constants.EMPTY
        MainActivity.INSTANCE?.toolbarVisibility(true)
    }

    override fun onPause() {
        super.onPause()
        MainActivity.INSTANCE?.toolbarVisibility(false)
    }

    override fun resolveDependency() {
       DaggerMoviesDetailComponent.builder()
               .applicationComponent(MyApplication.applicationComponent)
               .moviesDetailModule(MoviesDetailModule(this))
               .build().injectView(this)
    }

    override fun retry() {
       
    }

    override fun setDetails(moviesItem: MoviesItem?){

        moviesTitle?.text = moviesItem?.originalTitle

        if(!TextUtils.isEmpty(moviesItem?.releaseDate))
            moviesReleaseDate?.text = getString(R.string.release_date).plus(DateUtility.getDayNameAndMonthNameFormat(moviesItem?.releaseDate!!))

        if(!TextUtils.isEmpty(moviesItem?.posterPath)){
            Picasso.get().load(BuildConfig.IMAGE_BASE.plus(moviesItem?.posterPath)).into(propertyImage, object : Callback {
                override fun onSuccess() {
                    Timber.d(Constants.ON_SUCCESS)
                    RxView.clicks(propertyImage!!).subscribe { ZoomInZoomOutImageDialog(activity!!, BuildConfig.IMAGE_BASE.plus(moviesItem?.posterPath)) }
                }

                override fun onError(e: Exception?) {
                    Timber.d("${Constants.ON_ERROR} $e")
                }

            })
        }else if(!TextUtils.isEmpty(moviesItem?.backdropPath)){
            Picasso.get().load(BuildConfig.IMAGE_BASE.plus(moviesItem?.backdropPath)).into(propertyImage, object : Callback {
                override fun onSuccess() {
                    Timber.d(Constants.ON_SUCCESS)
                    RxView.clicks(propertyImage!!).subscribe { ZoomInZoomOutImageDialog(activity!!, BuildConfig.IMAGE_BASE.plus(moviesItem?.backdropPath)) }

                }

                override fun onError(e: Exception?) {
                    Timber.d("${Constants.ON_ERROR} $e")
                }

            })
        }

        if(!TextUtils.isEmpty(moviesItem?.backdropPath)){
            Picasso.get().load(BuildConfig.IMAGE_BASE.plus(moviesItem?.backdropPath)).into(posterImage, object : Callback {
                override fun onSuccess() {
                    Timber.d(Constants.ON_SUCCESS)
                    RxView.clicks(posterImage!!).subscribe { ZoomInZoomOutImageDialog(activity!!, BuildConfig.IMAGE_BASE.plus(moviesItem?.backdropPath)) }

                }

                override fun onError(e: Exception?) {
                    Timber.d("${Constants.ON_ERROR} $e")
                }

            })

            if(moviesItem?.video!!){
                playIcon?.visibility = View.VISIBLE
            }
        }

        moviesDescription?.text = moviesItem?.overview

    }

    override fun getObject(): MoviesItem?{
        return arguments?.getParcelable(Constants.OBJECT)
    }
}