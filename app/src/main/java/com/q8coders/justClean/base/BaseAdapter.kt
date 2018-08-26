package com.q8coders.justClean.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * @Created by shahid on 8/26/2018.
 */
abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var items: LinkedList<T> = LinkedList()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindViewHold(holder, position)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(setItemLayout(), parent, false)
        return onCreateViewHold(view)
    }

    protected fun addData(item : T){
        items.add(item)

        if(itemCount>0)
        notifyItemInserted(itemCount-1)
    }

    protected fun addDataAsList(item : MutableList<T>){
        items.addAll(item)
        notifyDataSetChanged()
    }

    protected fun getData(position: Int) : T = items[position]


    protected fun removeData(position: Int){
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    abstract fun setItemLayout(): Int
    abstract fun onBindViewHold(holder: RecyclerView.ViewHolder?, position: Int)
    abstract fun onCreateViewHold(view : View): RecyclerView.ViewHolder
    abstract fun addDataInToList(item : T)
    abstract fun getItem(position: Int) : T


}