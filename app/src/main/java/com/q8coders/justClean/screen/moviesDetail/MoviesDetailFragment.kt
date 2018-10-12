package com.q8coders.justClean.screen.moviesDetail

import android.os.Build
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.q8coders.justClean.BuildConfig
import com.q8coders.justClean.R
import com.q8coders.justClean.application.MyApplication
import com.q8coders.justClean.base.BaseFragment
import com.q8coders.justClean.di.components.DaggerMoviesDetailComponent
import com.q8coders.justClean.di.modules.MoviesDetailModule
import com.q8coders.justClean.model.moviesModel.MoviesItem
import com.q8coders.justClean.screen.main.MainActivity
import com.q8coders.justClean.screen.movies.GenresUtil
import com.q8coders.justClean.utility.Constants
import com.q8coders.justClean.utility.DateUtility
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_detail_content.*
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import android.support.v4.content.ContextCompat
import android.view.WindowManager
import android.support.v7.graphics.Palette
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.squareup.picasso.Target


/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
class MoviesDetailFragment : BaseFragment(), MoviesDetailView {

    @Inject
    lateinit var moviesDetailPresenterImp: MoviesDetailPresenterImp

    override fun setLayoutResource(): Int = R.layout.movies_detail_fragment

    override fun viewIsReady() {
        moviesDetailPresenterImp.init()
    }

    override fun setFragmentTitle(actionBarTitle: TextView?, text: String?) {
        actionBarTitle?.text = Constants.EMPTY
        MainActivity.INSTANCE?.toolbarVisibility(true)
    }

    override fun onPause() {
        super.onPause()
        setStatusBarColor(ContextCompat.getColor(activity!!, R.color.colorPrimaryDark))
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

    override fun setDetails(moviesItem: MoviesItem?) {

        moviesDuration?.text = GenresUtil.getGenresText(moviesItem?.genreIds)
        moviesTitle?.text = moviesItem?.originalTitle
        populartyValue?.text = moviesItem?.popularity?.toString()
        isAdultValue?.text = moviesItem?.adult?.toString()
        voteValue?.text = moviesItem?.voteCount?.toString()
        voteAverageValue?.text = moviesItem?.voteAverage?.toString()

        if (!TextUtils.isEmpty(moviesItem?.releaseDate))
            moviesReleaseDate?.text = getString(R.string.release_date).plus(DateUtility.getDayNameAndMonthNameFormat(moviesItem?.releaseDate!!))

        if (!TextUtils.isEmpty(moviesItem?.posterPath)) {
            Picasso.get().load(BuildConfig.IMAGE_BASE.plus(moviesItem?.posterPath)).into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    if (bitmap != null) {
                        getImageColor(bitmap)
                        propertyImage.setImageBitmap(bitmap)
                    }

                }
            })
        } else if (!TextUtils.isEmpty(moviesItem?.backdropPath)) {
            Picasso.get().load(BuildConfig.IMAGE_BASE.plus(moviesItem?.backdropPath)).into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    if (bitmap != null) {
                        getImageColor(bitmap)
                        propertyImage.setImageBitmap(bitmap)
                    }
                }

            })
        }

        if (!TextUtils.isEmpty(moviesItem?.backdropPath)) {
            Picasso.get().load(BuildConfig.IMAGE_BASE.plus(moviesItem?.backdropPath)).into(posterImage, object : Callback {
                override fun onSuccess() {
                    Timber.d(Constants.ON_SUCCESS)
                }

                override fun onError(e: Exception?) {
                    Timber.d("${Constants.ON_ERROR} $e")
                }

            })

            if (moviesItem?.video!!) {
                playIcon?.visibility = View.VISIBLE
            }
        }

        moviesDescription?.text = moviesItem?.overview

    }

    override fun getObject(): MoviesItem? {
        return arguments?.getParcelable(Constants.OBJECT)
    }

    private fun getImageColor(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            setStatusBarColor(palette!!.getMutedColor(R.attr.colorPrimary))
        }
    }

    private fun setStatusBarColor(color: Int?) {
        var mutedColor: Int? = color
        val window = activity?.window

        // clear FLAG_TRANSLUCENT_STATUS flag:
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        // finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (mutedColor == null) {
                mutedColor = ContextCompat.getColor(activity!!, R.color.translucent)
            }
            window?.statusBarColor = mutedColor
        }
    }
}