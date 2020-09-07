package com.backbase.assignment.ui.features.movieList.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import kotlinx.android.synthetic.main.title_item.view.*

class TitleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(title: String?) {
        if (title != null) {
            itemView.txtTitle.text = title
        }
    }

    companion object {
        fun create(parent: ViewGroup): TitleViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.title_item, parent, false)
            return TitleViewHolder(
                view
            )
        }
    }
}
