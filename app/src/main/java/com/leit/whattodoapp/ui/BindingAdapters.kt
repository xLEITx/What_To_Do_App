package com.leit.whattodoapp.ui

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.leit.whattodoapp.R
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
            textView.text = textView.context.getString(R.string.loading)
        }
    }

}

@BindingAdapter("status")
fun bindButtonStatus(button: Button, status: Status){
    when(status){
        Status.SUCCESS ->{
            button.isEnabled = true
        }

        Status.ERROR ->{
            button.isEnabled = false
        }

        Status.LOADING ->{
            button.isEnabled = false
        }
    }

}
