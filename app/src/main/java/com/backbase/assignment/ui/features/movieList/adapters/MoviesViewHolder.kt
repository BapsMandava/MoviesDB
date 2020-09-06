package com.backbase.assignment.ui.features.movieList.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.ui.model.Results
import kotlinx.android.synthetic.main.movie_item.view.*


class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(news: Results?) {
        if (news != null) {
            itemView.title.text = news.title
          /*  if (!news.image.isNullOrEmpty())
                Picasso.get().load(news.image).into(itemView.img_news_banner)*/
        }
    }

    companion object {
        fun create(parent: ViewGroup): MoviesViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item, parent, false)
            return MoviesViewHolder(view)
        }
    }
}