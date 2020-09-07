package com.backbase.assignment.ui.features.movieList.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.ui.model.Results
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.now_playing_list_item.view.*

class NowPlayingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(context: Context, movieData: Results?) {
        if (movieData != null) {
            if (!movieData.poster_path.isNullOrEmpty())
                Glide.with(context).load("https://image.tmdb.org/t/p/original/"+movieData.poster_path).into(itemView.ivPoster)
        }
    }

    companion object {
        fun create(parent: ViewGroup): NowPlayingViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.now_playing_list_item, parent, false)
            return NowPlayingViewHolder(
                view
            )
        }
    }
}