package com.backbase.assignment.ui.features.movieList.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.ui.features.movieList.viewholders.NowPlayingViewHolder
import com.backbase.assignment.ui.model.Results

class NowPlayingListAdapter internal constructor(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var moviesListNowPlaying = emptyList<Results>()
    private var context: Context = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
        return NowPlayingViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NowPlayingViewHolder).bind(context,moviesListNowPlaying.get(position))
    }

    override fun getItemCount() = moviesListNowPlaying.size

    internal fun setRepos(repos: List<Results>) {
        this.moviesListNowPlaying = repos
        notifyDataSetChanged()
    }
    fun clear() {
        moviesListNowPlaying = emptyList()
        notifyDataSetChanged()
    }

}