package com.backbase.assignment.ui.features.movieList.adapters

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.ui.data.State
import com.backbase.assignment.ui.features.movieList.viewholders.ListFooterViewHolder
import com.backbase.assignment.ui.features.movieList.viewholders.MoviesViewHolder
import com.backbase.assignment.ui.features.movieList.viewholders.NowPlayingListViewHolder
import com.backbase.assignment.ui.features.movieList.viewholders.TitleViewHolder
import com.backbase.assignment.ui.model.Results


class MoviesListAdapter(context: Context, private val retry: () -> Unit,val movieDataOnClick : (Bundle) -> Unit)
    : PagedListAdapter<Results, RecyclerView.ViewHolder>(MoviesDiffCallback) {

    private val TITLE_NOW_PLAYING_VIEW_TYPE = 0
    private val NOW_PLAYING_TYPE = 1
    private val TITLE_POPULAR_VIEW_TYPE = 2
    private val POPULAR_VIEW_TYPE = 3
    private val FOOTER_VIEW_TYPE = 4

    private var context = context
    private var state = State.LOADING
    private var nowPlayingData = emptyList<Results>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TITLE_NOW_PLAYING_VIEW_TYPE) {
            return TitleViewHolder.create(parent)
        } else if (viewType == NOW_PLAYING_TYPE) {
            return NowPlayingListViewHolder.create(parent)
        } else if (viewType == TITLE_POPULAR_VIEW_TYPE) {
            return TitleViewHolder.create(parent)
        } else if (viewType == POPULAR_VIEW_TYPE) {
           return MoviesViewHolder.create(parent)
        } else
            return ListFooterViewHolder.create(retry, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TITLE_NOW_PLAYING_VIEW_TYPE)
            (holder as TitleViewHolder).bind("Playing now")
        else if (getItemViewType(position) == NOW_PLAYING_TYPE)
            (holder as NowPlayingListViewHolder).bind(context,nowPlayingData)
        else if (getItemViewType(position) == TITLE_POPULAR_VIEW_TYPE)
            (holder as TitleViewHolder).bind("Most popular")
        else if (getItemViewType(position) == POPULAR_VIEW_TYPE)
            (holder as MoviesViewHolder).bind(context = context,movieData = getItem(position-3),adapterOnClick = { item -> doClick(item) })
        else (holder as ListFooterViewHolder).bind(state)
    }

    override fun getItemViewType(position: Int): Int {
        when(position){
            0-> return TITLE_NOW_PLAYING_VIEW_TYPE
            1-> return NOW_PLAYING_TYPE
            2-> return TITLE_POPULAR_VIEW_TYPE
            else -> {
                return if (position < super.getItemCount()) POPULAR_VIEW_TYPE else FOOTER_VIEW_TYPE
            }
        }
    }

    companion object {
        val MoviesDiffCallback = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

    fun setNowPlayingData(nowPlayingData: List<Results>) {
        this.nowPlayingData = nowPlayingData
        notifyDataSetChanged()
    }

    fun clear() {
        nowPlayingData = emptyList()
        notifyDataSetChanged()
    }

    fun doClick(data:Bundle){
        movieDataOnClick(data)
    }
}