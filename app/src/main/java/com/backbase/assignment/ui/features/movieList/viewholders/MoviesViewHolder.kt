package com.backbase.assignment.ui.features.movieList.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.ui.model.Results
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item.view.*


class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(context: Context, movieData: Results?) {
        if (movieData != null) {
            itemView.title.text = movieData.title
            if (!movieData.poster_path.isNullOrEmpty())
                Glide.with(context).load("https://image.tmdb.org/t/p/original/"+movieData.poster_path).into(itemView.poster)
                //Picasso.get().load("https://image.tmdb.org/t/p/original/"+movieData.poster_path).into(itemView.poster)
        }
    }

    companion object {
        fun create(parent: ViewGroup): MoviesViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item, parent, false)
            return MoviesViewHolder(
                view
            )
        }
    }
}