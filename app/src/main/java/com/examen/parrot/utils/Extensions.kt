package com.examen.parrot.utils

import androidx.lifecycle.MutableLiveData

object Extensions {

    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
}