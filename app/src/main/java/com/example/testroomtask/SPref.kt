package com.example.testroomtask

import android.content.SharedPreferences

object SPref {
    const val PREF_USER_CRED = "usercred"
    const val isfFirtTime = "isfFirtTime"


    fun StrogingSingleString(
        sharedPreferences: SharedPreferences,
        key: String?,
        values: String?
    ) {
        val editor = sharedPreferences.edit()
        editor.putString(key, values)
        editor.commit()
    }

    fun getStringPref(sharedPreferences: SharedPreferences, key: String?): String {
        return sharedPreferences.getString(key, "")
    }

}