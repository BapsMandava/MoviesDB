package com.backbase.assignment.ui.custom


import android.R
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


/**
 * Image view extension class that helps to load an image
 *
 * and other Image view Ui modifications
 *
 */


/**
 * Method gets the Image from the path specified in the parameter
 * this also circle crops the Image.
 * @param path  image url
 */
@BindingAdapter("getImage")
fun AppCompatImageView.getImage(path: String?) {
    Glide.with(context).load("https://image.tmdb.org/t/p/original/"+path).diskCacheStrategy(DiskCacheStrategy.ALL).into(this)
}