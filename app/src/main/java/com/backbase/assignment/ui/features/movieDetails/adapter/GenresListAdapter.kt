package com.backbase.assignment.ui.features.movieDetails.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.ui.features.movieDetails.viewholders.GenresViewHolder
import com.backbase.assignment.ui.features.movieList.viewholders.NowPlayingViewHolder
import com.backbase.assignment.ui.model.Genres
import com.backbase.assignment.ui.model.Results

class GenresListAdapter internal constructor(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var genresItem = emptyList<Genres>()
    private var context: Context = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
        return GenresViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GenresViewHolder).bind(genresItem.get(position).name)
    }

    override fun getItemCount() = genresItem.size

    internal fun setRepos(repos: List<Genres>) {
        this.genresItem = repos
        notifyDataSetChanged()
    }
    fun clear() {
        genresItem = emptyList()
        notifyDataSetChanged()
    }

}