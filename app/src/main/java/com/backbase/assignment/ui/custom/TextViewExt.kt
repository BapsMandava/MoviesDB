package com.backbase.assignment.ui.custom


import android.widget.TextView
import androidx.databinding.BindingAdapter
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
        this.text = newF.format(dateValue)
    }
    else{
        this.text = ""
    }
}

@BindingAdapter("getTime")
fun TextView.getTime(time: Int?) {
    val hours: Int = time?.div(60) ?: 0
    val minutes: Int = time?.rem(60) ?: 0
    this.text=" - "+hours+"h"+" "+minutes+"m"
}