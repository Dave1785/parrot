package com.examen.parrot.login.framework

import android.content.Context
import android.content.SharedPreferences
import com.examen.parrot.login.domain.ResponseLogin

class UserPreferences(context: Context) {

    private var sharedPreferences: SharedPreferences
    private var editorSharedPreferences: SharedPreferences.Editor


    enum class DataType {
        TOKEN, REFRESH_TOKEN
    }

    init {
        sharedPreferences = context.getSharedPreferences(KEY_SHARED_PREFERENCES, 0)
        editorSharedPreferences = sharedPreferences.edit()
    }


    companion object {
        var userPreferences: UserPreferences? = null
        val KEY_SHARED_PREFERENCES = "PARROT_SHARED_PREFERENCES"
        val KEY_TOKEN = "TOKEN"
        val KEY_REFRESH_TOKEN = "REFRESH_TOKEN"

        fun getInstance(context: Context): UserPreferences {
            return if (userPreferences == null) {
                UserPreferences(context)
            } else {
                userPreferences as UserPreferences
            }
        }
    }


    fun saveUser(authenticate: ResponseLogin) {
        editorSharedPreferences.putString(KEY_TOKEN, authenticate.access)
        editorSharedPreferences.putString(KEY_REFRESH_TOKEN, authenticate.refresh)
        editorSharedPreferences.apply()
    }



    fun getValue(dataType: DataType): Any? {
        var data: Any?
        when (dataType) {

            DataType.TOKEN -> {
                data = sharedPreferences.getString(KEY_TOKEN, "")
            }
            DataType.REFRESH_TOKEN -> {
                data = sharedPreferences.getString(KEY_REFRESH_TOKEN, "")
            }

        }
        return data!!
    }

    fun clearData() {
        editorSharedPreferences.clear().apply()
    }
}