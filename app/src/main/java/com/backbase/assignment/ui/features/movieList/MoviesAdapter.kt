package com.backbase.assignment.ui.features.movieList

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.ui.custom.RatingView
import com.backbase.assignment.ui.model.Results
import com.google.gson.JsonArray
import com.google.gson.JsonObject

class MoviesAdapter internal constructor(context: Context, val adapterOnClick : (Bundle) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.RepoViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var repos = emptyList<Results>()
    private var context: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemView = inflater.inflate(R.layout.movie_item, parent, false)
        return RepoViewHolder(itemView)
    }

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val date: TextView = itemView.findViewById(R.id.releaseDate)
        fun setItem(item: Int,avatar:String,title:String) {
            var data = Bundle()
            data.putString("avatar",avatar)
            data.putInt("id",item)
            data.putString("title",title)
           // container.setOnClickListener { adapterOnClick(data) }
        }
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val current = repos[position]
        holder.title.text = current.title

       // Glide.with(context).load(current.avatar).placeholder(R.drawable.ic_broken_image).into(holder.image)
        holder.date.text = current.original_title
       // holder.setItem(current.Id,current.avatar,current.title)

    }

    internal fun setRepos(repos: List<Results>) {
        this.repos = repos
        notifyDataSetChanged()
    }

    override fun getItemCount() = repos.size

    fun clear() {
        repos = emptyList()
        notifyDataSetChanged()
    }
}