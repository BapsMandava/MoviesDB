package com.backbase.assignment.ui.custom

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.backbase.assignment.R
import kotlinx.android.synthetic.main.custom_rating.view.*

class RatingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_rating, this, true)

        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(it, R.styleable.customRatingElement, 0, 0)
            val percentage =
                resources.getText(typedArray.getResourceId(R.styleable.customRatingElement_percentage,R.string.zero))

            tvPercentage.text = percentage
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
              //  progressBar.setProgress(percentage,true)
                //progressBar.gradin
            }
            typedArray.recycle()
        }
    }
}