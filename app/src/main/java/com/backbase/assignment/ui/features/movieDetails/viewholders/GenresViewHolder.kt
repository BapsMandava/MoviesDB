package com.backbase.assignment.ui.features.movieDetails.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import kotlinx.android.synthetic.main.title_item.view.*

class GenresViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(title: String?) {
        if (title != null) {
            itemView.txtTitle.text = title
        }
    }

    companion object {
        fun create(parent: ViewGroup): GenresViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.genres_item, parent, false)
            return GenresViewHolder(
                view
            )
        }
    }
}
