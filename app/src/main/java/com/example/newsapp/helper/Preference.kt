package com.example.newsapp.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit


private const val IS_AUTHORIZATED = "is_auhorizated"

class SharedPreference(context: Context) {

    private var pref: SharedPreferences =
        context.getSharedPreferences("NewsApp", Context.MODE_PRIVATE)

    var clear: SharedPreferences.Editor = pref.edit().clear()

    var authorized: Boolean
        get() {
            return pref.getBoolean(IS_AUTHORIZATED, false)
        }
        set(value) {
            pref.edit {
                putBoolean(IS_AUTHORIZATED, value)
            }
        }

}
