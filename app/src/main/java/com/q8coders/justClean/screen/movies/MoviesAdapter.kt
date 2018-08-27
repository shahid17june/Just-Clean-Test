package com.q8coders.justClean.screen.movies

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.RelativeLayout
import com.jakewharton.rxbinding2.view.RxView
import com.q8coders.justClean.BuildConfig
import com.q8coders.justClean.R
import com.q8coders.justClean.base.BaseAdapter
import com.q8coders.justClean.model.moviesModel.MoviesItem
import com.q8coders.justClean.utility.*
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_item.view.*
import timber.log.Timber
import java.lang.Exception


/**
 * @Created by shahid on 8/26/2018.
 */
class MoviesAdapter constructor(private val isSearch: Boolean, recyclerView: RecyclerView, layoutManager: LinearLayoutManager)
    : BaseAdapter<MoviesItem>() {

    private var recyclerClickListner: RecyclerClickListner? = null
    private var paginationRecyler: PaginationRecyler? = null

    init {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //To check if  recycler last Visible Item
                val totalItem = layoutManager.itemCount
                val lastVisiblePos = layoutManager.findLastCompletelyVisibleItemPosition()

                if (totalItem % Constants.TOTAL_COUNT == 0 && totalItem.minus(4) == lastVisiblePos) {
                    paginationRecyler?.pagination()
                }
            }
        })
    }

    override fun setItemLayout(): Int = R.layout.movies_item

    override fun onBindViewHold(holder: RecyclerView.ViewHolder?, position: Int) {
        val myHolder = holder as MyViewHolder
        val context = myHolder.itemView.context
        val model : MoviesItem? = items[position]

        if(isSearch){
            holder.container?.layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        }


        holder.moviesTitle?.text = model?.originalTitle

        if(!TextUtils.isEmpty(model?.releaseDate))
        holder.moviesReleaseDate?.text = context.getString(R.string.release_date).plus(DateUtility.getDayNameAndMonthNameFormat(model?.releaseDate!!))

        if(!TextUtils.isEmpty(model?.posterPath)){
            Picasso.get().load(BuildConfig.IMAGE_BASE.plus(model?.posterPath)).into(holder.moviesImage, object :Callback{
                override fun onSuccess() {
                    Timber.d(Constants.ON_SUCCESS)
                    RxView.clicks(myHolder.moviesImage!!).subscribe { recyclerClickListner?.itemClicked(position, BuildConfig.IMAGE_BASE.plus(model?.posterPath)) }
                }

                override fun onError(e: Exception?) {
                    Timber.d("${Constants.ON_ERROR} $e")
                }

            })
        }else if(!TextUtils.isEmpty(model?.backdropPath)){
            Picasso.get().load(BuildConfig.IMAGE_BASE.plus(model?.backdropPath)).into(holder.moviesImage, object :Callback{
                override fun onSuccess() {
                    Timber.d(Constants.ON_SUCCESS)
                    RxView.clicks(myHolder.moviesImage!!).subscribe { recyclerClickListner?.itemClicked(position, BuildConfig.IMAGE_BASE.plus(model?.backdropPath)) }
                }

                override fun onError(e: Exception?) {
                    Timber.d("${Constants.ON_ERROR} $e")
                }

            })
        }

        RxView.clicks(myHolder.itemView!!).subscribe { recyclerClickListner?.itemClicked(position, Constants.ITEM_CLICKED) }
        RxView.clicks(myHolder.buy!!).subscribe { recyclerClickListner?.itemClicked(position, Constants.ITEM_CLICKED) }



    }

    override fun onCreateViewHold(view: View): RecyclerView.ViewHolder {
        return MyViewHolder(view)
    }

    override fun addDataInToList(item: MoviesItem) {
        addData(item)
    }

    fun addDataAsBulkList(items: MutableList<MoviesItem>) {
        addDataAsList(items)
    }

    fun clearAllData(){
        items.clear()
        notifyDataSetChanged()
    }


    override fun getItem(position: Int): MoviesItem {
        return items[position]
    }

    inner class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val moviesDuration = itemView?.moviesDuration
        val moviesReleaseDate = itemView?.moviesReleaseDate
        val moviesTitle = itemView?.moviesTitle
        val moviesImage = itemView?.moviesImage
        val buy = itemView?.buy
        val container = itemView?.container
    }


    fun setOnClickListner(recyclerClickListner: RecyclerClickListner) {
        this.recyclerClickListner = recyclerClickListner
    }

}