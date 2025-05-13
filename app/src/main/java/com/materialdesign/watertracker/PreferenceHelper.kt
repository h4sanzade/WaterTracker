package com.materialdesign.watertracker


import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {
    private const val PREF_NAME = "MyPrefs"
    private const val KEY_USERNAME = "username"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }


    fun isFirstLaunch(context: Context): Boolean {
        return !getPrefs(context).contains(KEY_USERNAME)
    }


    fun saveUsername(context: Context, username: String) {
        getPrefs(context).edit().putString(KEY_USERNAME, username).apply()
    }

    fun getUsername(context: Context): String? {
        return getPrefs(context).getString(KEY_USERNAME, null)
    }
}