package com.q8coders.justClean.screen.movies

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.jakewharton.rxbinding2.view.RxView
import com.q8coders.justClean.BuildConfig
import com.q8coders.justClean.R
import com.q8coders.justClean.base.BaseAdapter
import com.q8coders.justClean.model.moviesModel.MoviesItem
import com.q8coders.justClean.utility.*
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_movies_item.view.*
import timber.log.Timber
import java.lang.Exception
import java.util.concurrent.TimeUnit


/*
 * Created by Shahid Akhtar on 13/10/18.
 * Copyright © 2018 Shahid Akhtar. All rights reserved.
*/
class MyMoviesAdapter constructor(private val isFromSearch: Boolean) : BaseAdapter<MoviesItem>() {

    private var recyclerClickListner: RecyclerClickListner? = null

    override fun setItemLayout(): Int {
        return if (isFromSearch) {
            R.layout.search_movies_item
        } else {
            R.layout.my_movies_item
        }
    }


    override fun onBindViewHold(holder: RecyclerView.ViewHolder?, position: Int) {

        val model: MoviesItem? = items[position]

        when (holder) {
            is MyViewHolder -> {
                if (!TextUtils.isEmpty(model?.posterPath)) {
                    Picasso.get().load(BuildConfig.IMAGE_BASE.plus(model?.posterPath)).into(holder.moviesImage, object : Callback {
                        override fun onSuccess() {
                            Timber.d(Constants.ON_SUCCESS)
                        }

                        override fun onError(e: Exception?) {
                            Timber.d("${Constants.ON_ERROR} $e")
                        }

                    })
                } else if (!TextUtils.isEmpty(model?.backdropPath)) {
                    Picasso.get().load(BuildConfig.IMAGE_BASE.plus(model?.backdropPath)).into(holder.moviesImage, object : Callback {
                        override fun onSuccess() {
                            Timber.d(Constants.ON_SUCCESS)
                        }

                        override fun onError(e: Exception?) {
                            Timber.d("${Constants.ON_ERROR} $e")
                        }

                    })
                }

                addDisposable(RxView.clicks(holder.moviesImage!!).throttleFirst(2, TimeUnit.SECONDS)
                        .subscribe { recyclerClickListner?.itemClicked(position, Constants.ITEM_CLICKED)})
            }

            is MySearchViewHolder->{
                val context = holder.itemView.context

                holder.moviesDuration?.text = GenresUtil.getGenresText(model?.genreIds)

                holder.moviesTitle?.text = model?.originalTitle

                if(!TextUtils.isEmpty(model?.releaseDate))
                    holder.moviesReleaseDate?.text = context.getString(R.string.release_date).plus(DateUtility.getDayNameAndMonthNameFormat(model?.releaseDate!!))

                if(!TextUtils.isEmpty(model?.posterPath)){
                    Picasso.get().load(BuildConfig.IMAGE_BASE.plus(model?.posterPath)).into(holder.moviesImage, object :Callback{
                        override fun onSuccess() {
                            Timber.d(Constants.ON_SUCCESS)
                        }

                        override fun onError(e: Exception?) {
                            Timber.d("${Constants.ON_ERROR} $e")
                        }

                    })
                }else if(!TextUtils.isEmpty(model?.backdropPath)){
                    Picasso.get().load(BuildConfig.IMAGE_BASE.plus(model?.backdropPath)).into(holder.moviesImage, object :Callback{
                        override fun onSuccess() {
                            Timber.d(Constants.ON_SUCCESS)
                        }

                        override fun onError(e: Exception?) {
                            Timber.d("${Constants.ON_ERROR} $e")
                        }

                    })
                }


                addDisposable(RxView.clicks(holder.container!!).throttleFirst(2, TimeUnit.SECONDS)
                        .subscribe { recyclerClickListner?.itemClicked(position, Constants.ITEM_CLICKED) })

                addDisposable(RxView.clicks(holder.moviesImage!!).throttleFirst(2, TimeUnit.SECONDS)
                        .subscribe { recyclerClickListner?.itemClicked(position, Constants.ITEM_CLICKED) })

            }
        }


    }

    override fun onCreateViewHold(view: View): RecyclerView.ViewHolder {
        return if (isFromSearch) {
            MySearchViewHolder(view)
        } else {
            MyViewHolder(view)
        }
    }

    override fun addDataInToList(item: MoviesItem) {
        addData(item)
    }

    fun addDataAsBulkList(items: MutableList<MoviesItem>) {
        addDataAsList(items)
    }

    fun clearAllData() {
        items.clear()
        notifyDataSetChanged()
    }


    override fun getItem(position: Int): MoviesItem {
        return items[position]
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moviesImage:ImageView? = itemView.moviesImage
    }

    internal inner class MySearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moviesDuration: MyTextView? = itemView.moviesDuration
        val moviesReleaseDate: MyTextView? = itemView.moviesReleaseDate
        val moviesTitle:MyTextView? = itemView.moviesTitle
        val moviesImage:ImageView? = itemView.moviesImage
        val container:RelativeLayout? = itemView.container
    }


    fun setOnClickListner(recyclerClickListner: RecyclerClickListner) {
        this.recyclerClickListner = recyclerClickListner
    }

}