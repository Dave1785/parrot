package com.examen.parrot.utils

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter


object BindingAdapters {

    @BindingAdapter("android:visibilityBoolean")
    @JvmStatic
    fun setVisibility(view: View, value: Boolean) {
        view.visibility = if (value) View.VISIBLE else View.GONE
    }

    @BindingAdapter("android:itemsCount")
    @JvmStatic
    fun setItemsCount(textView: TextView, items:String) {
        textView.text="Numero de productos $items"
    }

}