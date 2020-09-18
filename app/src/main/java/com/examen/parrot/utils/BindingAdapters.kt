package com.examen.parrot.utils

import android.view.View
import android.widget.Button
import androidx.databinding.BindingAdapter


object BindingAdapters {

    @BindingAdapter("android:visibilityBoolean")
    @JvmStatic
    fun setVisibility(view: View, value: Boolean) {
        view.visibility = if (value) View.VISIBLE else View.GONE
    }

}