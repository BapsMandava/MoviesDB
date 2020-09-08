package com.backbase.assignment.ui.custom


import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.now_playing_list_item.view.*
import java.text.SimpleDateFormat

/**
 * Image view extension class that helps to load an image
 *
 * and other Image view Ui modifications
 *
 */



@BindingAdapter("getDate")
fun TextView.getDate(date: String?) {
    if(!date.isNullOrEmpty()) {
        val dateValue = SimpleDateFormat("yyyy-MM-dd").parse(date)
        val newF = SimpleDateFormat("MMM dd, YYYY")
        newF.format(dateValue)
    }
    else{
         ""
    }
}