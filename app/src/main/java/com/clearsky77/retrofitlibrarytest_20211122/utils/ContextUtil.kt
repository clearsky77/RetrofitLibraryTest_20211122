package com.clearsky77.retrofitlibrarytest_20211122.utils

import android.content.Context

class ContextUtil {

    companion object{
        // 어떤 파일에
        // 어떤 데이터 항목에
        // setter / getter

        // 어떤 파일에
        private val prefName = "RetrofitTestPref"
        // 어떤 데이터 항목에
        private val TOKEN = "TOKEN"
        // setter / getter
        fun setToken(context: Context, token: String){
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(TOKEN, token).apply()

        }

    }

}