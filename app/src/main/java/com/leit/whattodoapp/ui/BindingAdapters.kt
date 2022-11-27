package com.leit.whattodoapp.ui

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.leit.whattodoapp.model.Status

@BindingAdapter("status")
fun bindStatus(textView:TextView, status: Status){
    when(status){
        Status.SUCCESS ->{
            textView.visibility = View.VISIBLE
        }

        Status.ERROR ->{
            textView.visibility = View.INVISIBLE
        }

        Status.LOADING ->{
            textView.visibility = View.INVISIBLE
        }
    }

}