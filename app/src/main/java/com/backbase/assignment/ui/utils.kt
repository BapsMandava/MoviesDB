package com.backbase.assignment.ui

import java.text.SimpleDateFormat
import java.util.*

object utils {

    fun dateFormat(date:String?):String {
        if(!date.isNullOrEmpty()) {
            val dateValue = SimpleDateFormat("yyyy-MM-dd").parse(date)
            val newF = SimpleDateFormat("MMM dd, YYYY")
            return newF.format(dateValue)
        }
        else{
            return ""
        }
    }
}