package com.backbase.assignment.ui.features.movieList.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.ui.features.movieList.adapters.NowPlayingListAdapter
import com.backbase.assignment.ui.model.Results
import kotlinx.android.synthetic.main.now_playing_list.view.*


class NowPlayingListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(context:Context,nowPlayingList:List<Results>) {
        val adapter =
            NowPlayingListAdapter(
                context
            )
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        itemView.rvNowPlaying.setLayoutManager(linearLayoutManager)
        itemView.rvNowPlaying.setItemAnimator(DefaultItemAnimator())
        itemView.rvNowPlaying.setAdapter(adapter)
        adapter.clear()
        adapter.setRepos(nowPlayingList)
    }

    companion object {
        fun create(parent: ViewGroup): NowPlayingListViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.now_playing_list, parent, false)
            return NowPlayingListViewHolder(
                view
            )
        }
    }
}